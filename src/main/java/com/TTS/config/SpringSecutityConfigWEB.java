package com.TTS.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Order(1)
@Configuration
@EnableWebSecurity
public class SpringSecutityConfigWEB extends WebSecurityConfigurerAdapter {
}
