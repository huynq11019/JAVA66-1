package com.TTS.sercurity.JWT;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.DomainUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenPovider {
	private final Logger _log = Logger.getLogger(DomainUserDetailsService.class);
	// đoạn mã bảo mật ở phí serve
	private String JWT_Serect = "java66lg";
	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 86_400_000;

	private Key key;
	@Autowired
	private DomainUserDetailsService userdetailservice;

	// tạo ra jwt từ thông tin user
	public String createToken(CustomUserDetail userDetail) {
		_log.debug("genarate token from" + userDetail.getUsername() +"id: "+ userDetail.getIdAccount());
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		String authroriries = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		System.out.println("token provider" + authroriries);	
//		return Jwts.builder()
//				.setSubject(Integer.toString(userDetail.getIdAccount()))
//				.setIssuedAt(now)
//				.setExpiration(expiryDate)
//				.signWith(SignatureAlgorithm.HS256, JWT_Serect)
//				.compact();
		String jwt = Jwts.builder().setSubject(Integer.toString(userDetail.getIdAccount()))
				.claim("auth", authroriries).signWith(SignatureAlgorithm.HS512, JWT_Serect)
				.setExpiration(expiryDate).compact();
		return jwt;
	}

	// get thông tin từ token
	public Authentication getUserFormToken(String token) {
		String base64EncodedKeyBytes = JWT_Serect;
		Claims claim = Jwts.parser().setSigningKey(base64EncodedKeyBytes).parseClaimsJws(token).getBody();
//		System.out.println(claim);
//		_log.info(claim.toString());
		//get authrority form tooken
		List<GrantedAuthority> authrorities = Arrays.stream(claim.get("auth").toString().split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		_log.info(authrorities);
		Integer accountId = Integer.parseInt(claim.getSubject());
		UserDetails userdetail = userdetailservice.loadUserByID(accountId);
		return new UsernamePasswordAuthenticationToken(userdetail, token, authrorities);
	}

	public boolean validateToken(String authToken) {
//		_log.info(authToken);
		try {
			Jwts.parser().setSigningKey(JWT_Serect).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
