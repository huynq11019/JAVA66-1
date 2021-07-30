package com.TTS;

import com.TTS.Util.AciArt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.Logger;

@SpringBootApplication
//@EnableJpaRepositories
@Slf4j
public class Java661Application {
	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	private static final String HTTP = "http";
	public static final String SERVER_SSL_KEY_STORE = "server.ssl.key-store";
	public static final String HTTPS = "https";
	public static final String SERVER_PORT = "server.port";
	public static final String SERVER_SERVLET_CONTEXT_PATH = "server.servlet.context-path";
	@Autowired
	private Environment env;

	public static void main(String[] args) {

//		SpringApplication.run(Java661Application.class, args);
		SpringApplication app = new SpringApplication(Java661Application.class);
		addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}
	public static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();
		/*
		 * The default profile to use when no other profiles are defined
		 * This cannot be set in the application.yml file.
		 * See https://github.com/spring-projects/spring-boot/issues/1219
		 */
		defProperties.put(SPRING_PROFILE_DEFAULT, "dev");
		app.setDefaultProperties(defProperties);

	}
	private static void logApplicationStartup(Environment env) {
		String protocol = HTTP;

		if (env.getProperty(SERVER_SSL_KEY_STORE) != null) {
			protocol = HTTPS;
		}

		String serverPort = env.getProperty(SERVER_PORT);
		String contextPath = env.getProperty(SERVER_SERVLET_CONTEXT_PATH);

		if (StringUtils.isEmpty(contextPath)) {
			contextPath = "/";
		}

		String hostAddress = "localhost";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}

		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t"
						+ "Local: \t\t{}://localhost:{}{}\n\t"
						+ "External: \t{}://{}:{}{}\n\t"
						+ "Profile(s): \t{}\n----------------------------------------------------------",
//				env.getProperty("spring.application.name")
				Java661Application.class.getName(), protocol, serverPort, contextPath,
				protocol, hostAddress,

				serverPort, contextPath, env.getActiveProfiles());

		serverStarted();
	}
	 private static void serverStarted() {
    AciArt asciiArt = new AciArt();
    	 
         String text = "FEC CODE TEAM";
         
         com.TTS.Util.AciArt.Settings settings = asciiArt.new Settings(new Font("SansSerif", Font.HANGING_BASELINE, 14), text.length() * 15, 15); // 30 pixel width per character
         
         asciiArt.drawString(text, "*", settings);
    }
}
