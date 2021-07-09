package com.TTS.sercurity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetail implements UserDetails {
	private static final long serialVersionUID = 7261895544371713946L;
	private Integer idAccount;
	private String emailLogin;
	private String passwordHash;;;;;;;;;;;;;;;;;;;;;;;;;;
	private List<GrantedAuthority> authrority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return account.getAuthrority().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authrority;
	}

	@Override
	public String getPassword() {
//		return account.getPasswordHash();
		return passwordHash;
	}

	@Override
	public String getUsername() {
		return emailLogin;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
