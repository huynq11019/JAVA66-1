package com.TTS.sercurity;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SercurityUtil {
	private SercurityUtil() {

	}
	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user.
	 */
	public static Optional<String> getCurrentUserLogin(){
		SecurityContext securitytContext = SecurityContextHolder.getContext();
		
		return Optional.ofNullable(securitytContext.getAuthentication()).map(authentication->{
			if(authentication.getPrincipal() instanceof UserDetails) {// nếu giá trị kia không phải kiểu thể hiện của UserDetail
				UserDetails springSecurity = (UserDetails) authentication.getPrincipal();
			}
			
			return null;
		});
	}
}
