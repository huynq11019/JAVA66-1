package com.TTS.sercurity;

import com.TTS.Util.CookieService;
import com.TTS.Util.ParamUtil;
import com.TTS.sercurity.JWT.TokenPovider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class RequestFilter extends OncePerRequestFilter {
//	private final Logger _log = Logger.getLogger(RequestFilter.class);
	@Autowired
	private TokenPovider jwtTokenProvider;
	@Autowired
	private DomainUserDetailsService userDetailService;
	@Autowired
	private ParamUtil param;
	@Autowired
	private CookieService cookie;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	String cookieValue =	cookie.getvalue("accesstoken");
//		log.info(cookieValue);
		try {
//			String token = param.getTokenFormRequest();
			String token = cookieValue;
//			_log.info("token"+token);
			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
				log.info(String.valueOf(jwtTokenProvider.validateToken(token)));
			    Authentication authentication = jwtTokenProvider.getUserFormToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.warn("xác thực người dùng không thành côngn",e);
		}
		filterChain.doFilter(request, response);
	}
	

}
