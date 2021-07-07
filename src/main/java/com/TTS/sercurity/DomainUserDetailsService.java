package com.TTS.sercurity;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.TTS.Repo.AccountRepo;
@Component("userDetailService")
public class DomainUserDetailsService implements UserDetailsService {
	private final Logger _log = Logger.getLogger(DomainUserDetailsService.class);
	@Autowired
	private  AccountRepo accountRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
