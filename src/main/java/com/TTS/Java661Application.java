package com.TTS;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.TTS.sercurity.DomainUserDetailsService;

@SpringBootApplication
//@EnableJpaRepositories
public class Java661Application {
	private final Logger _log = Logger.getLogger(DomainUserDetailsService.class);
	public static void main(String[] args) {
		SpringApplication.run(Java661Application.class, args);
		
	}

}
