package com.TTS.sercurity;

import com.TTS.Entity.Account;
import com.TTS.Repo.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("userDetailService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {
    //	private final Logger _log = Logger.getLogger(DomainUserDetailsService.class);
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByEmail(username)
                .orElseThrow(() -> new UserNotActiveException("user không tồn tại trong hệ thống"));
        if (account == null) {
            return null;
        }
//		System.out.println(account);
//       log.debug("");
        return new CustomUserDetail(account.getId(), account.getEmail(), account.getPasswordHash(),
                account.getAuthrority().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }

    public UserDetails loadUserByID(Integer id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user không tồn tại trong hệ thống"));
//		System.out.println(account);
//        log.info("đang load user detail từ accountID");
        return new CustomUserDetail(account.getId(), account.getEmail(), account.getPasswordHash(),
                account.getAuthrority().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));

    }

}
