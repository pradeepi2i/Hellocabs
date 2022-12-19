/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs;

import com.hellocabs.service.impl.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *   The overall control of application
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class HelloCabsApplication {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	/**
	 * <p>
	 *   Method where object creation and method call to access all
	 *   function in an application takes place
	 * </p>
	 *
	 * @param args command line argument
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelloCabsApplication.class, args);


	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowCredentials(true)
						.allowedHeaders("*")
						.allowedMethods("*");
			}
		};
	}
}
