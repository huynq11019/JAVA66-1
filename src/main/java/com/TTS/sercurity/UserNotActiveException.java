package com.TTS.sercurity;

import org.springframework.security.core.AuthenticationException;

public class UserNotActiveException extends AuthenticationException {


/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */


	private static final long serialVersionUID = 1L;

	public UserNotActiveException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public UserNotActiveException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

}
