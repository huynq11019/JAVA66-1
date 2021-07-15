package com.TTS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.TTS.sercurity.DomainUserDetailsService;
import com.TTS.sercurity.RequestFilter;
import com.google.common.collect.ImmutableList;

import lombok.RequiredArgsConstructor;
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DomainUserDetailsService userdetailService;
	@Autowired
	private RequestFilter requestFillter;
	@Autowired
	private JWTentrypoin jwtEntripoin;
	private static final String[] IGNOR_URLS = { "/app/**/*.{js,html}", "/i18n/**", "/content/**",
			"/swagger-ui/index.html", "/test/**" };
	private static final String[] PUBLIC_URL = { "/api/authenticate", "/api/register", "/api/forgotpassword", };
	
	private static final String[] AUTHENTICATED_URLS = { "/api/**" };
	
	

	 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// quản lý dữ liệu người dùng
		auth.userDetailsService(userdetailService).passwordEncoder(passwordEncoder());
//		auth.userDetailsService(uDAO).passwordEncoder(passwordEncoder());

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	  // To enable CORS
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(ImmutableList.of("*")); // www - obligatory
//        configuration.setAllowedOrigins(ImmutableList.of("*"));  //set access from all domains
//        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(ImmutableList.of("X-PINGOTHER","Authorization", "Cache-Control", "Content-Type"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers(IGNOR_URLS);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// phân quyền và sử dụng hình thwucs đăng nhập
//		httpSecurity.cors().and().csrf().disable();
		httpSecurity.cors().and()
        .csrf().disable()
        .formLogin().disable();
		  httpSecurity
              .headers()
              .contentSecurityPolicy("default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data:")
          .and()
              .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
          .and()
              .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
          .and()
              .frameOptions()
              .deny();
//		// Thực hiện xác thực với những request
//		httpSecurity.authorizeRequests().antMatchers("/api/admin/**").hasAnyAuthority("ADMIN").antMatchers(PUBLIC_URL)
//				.permitAll().antMatchers("/api/**").authenticated().anyRequest().permitAll();
//		// xử lý exception
		httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntripoin).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// thêm fillter để validate tokens với mọi request
		httpSecurity.addFilterBefore(requestFillter, UsernamePasswordAuthenticationFilter.class);
	}
}
