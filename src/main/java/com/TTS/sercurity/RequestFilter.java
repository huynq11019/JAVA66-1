package com.TTS.sercurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.TTS.Util.ParamUtil;
import com.TTS.sercurity.JWT.TokenPovider;
@Component
public class RequestFilter extends OncePerRequestFilter {
	private final Logger _log = Logger.getLogger(RequestFilter.class);
	@Autowired
	private TokenPovider jwtTokenProvider;
	@Autowired
	private DomainUserDetailsService userDetailService;
	@Autowired
	private ParamUtil param;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = param.getTokenFormRequest();
			_log.info(token);
		
			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
				_log.info(jwtTokenProvider.validateToken(token));
			    Authentication authentication = jwtTokenProvider.getUserFormToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			_log.warn("xác thực người dùng không thành côngn",e);
		}
		filterChain.doFilter(request, response);
	}
	

}
