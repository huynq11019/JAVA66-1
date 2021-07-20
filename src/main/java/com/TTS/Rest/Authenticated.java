package com.TTS.Rest;

import com.TTS.DTO.RequestLogin;
import com.TTS.Util.CookieService;
import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.JWT.JWTtoken;
import com.TTS.sercurity.JWT.TokenPovider;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@Slf4j
public class Authenticated {

//	private static final Logger log = LoggerFactory.getLogger(Authenticated.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenPovider tokenPovider;
	@Autowired
	private CookieService cookieService;

	@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"}, allowCredentials = "true",exposedHeaders = {"X-PINGOTHER","Authorization","Link,X-Total-Count","X-Action-Mesage","X-Action-Params"})
	@PostMapping("/authenticate")
	public ResponseEntity<JWTtoken> authenticateAccount(@Valid @RequestBody RequestLogin loginRequest)
			throws NotFoundException {
		log.info(loginRequest.toString());
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmailLogin(), loginRequest.getPassword()));
			CustomUserDetail custom = (CustomUserDetail) authentication.getPrincipal();
			log.info("test pricical: " + custom);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenPovider.createToken((CustomUserDetail) authentication.getPrincipal());

			cookieService.add("accesstoken", jwt, 200, true);
				
			return ResponseEntity.ok(new JWTtoken(jwt, "bruh", custom.getEmailLogin()));
		} catch (Exception e) {
			log.error("đăng nhập không thành công", e);
//			e.printStackTrace();
			throw new UsernameNotFoundException("tài khoản mật khẩu không chính xác");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@GetMapping("/ulogout")
	public ResponseEntity<String> LogOut(){
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("Đã Đăng xuất khở hệ thống");
	}

}
