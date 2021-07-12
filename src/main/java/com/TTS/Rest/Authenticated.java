package com.TTS.Rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TTS.DTO.RequestLogin;
import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.JWT.JWTtoken;
import com.TTS.sercurity.JWT.TokenPovider;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class Authenticated {

	private static final Logger log = LoggerFactory.getLogger(Authenticated.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenPovider tokenPovider;

	@PostMapping("/api/authenticate")
	public ResponseEntity<JWTtoken> authenticateAccount(@Valid @RequestBody RequestLogin loginRequest) throws NotFoundException {
		System.out.println(loginRequest);
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmailLogin(), loginRequest.getPassword()));
			CustomUserDetail custom = (CustomUserDetail) authentication.getPrincipal();
			System.out.println("test pricical: " + custom);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenPovider.createToken((CustomUserDetail) authentication.getPrincipal());
			return ResponseEntity.ok(new JWTtoken(jwt, "bruh", custom.getEmailLogin()));
		} catch (Exception e) {
			log.error("đăng nhập không thành công",e);
//			e.printStackTrace();
			throw new UsernameNotFoundException("tài khoản mật khẩu không chính xác");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
