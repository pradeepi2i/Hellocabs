/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.security;

import com.hellocabs.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <p>
 *   SecurityConfiguration class configures the security for Hello cab application
 *   providing the access to the url by authorized user
 * </p>
 *
 * @author : Divya
 * created on 03/11/2022
 * @version 1.0
 *
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * <p>
     *   This method returns the object BCryptPasswordEncoder {@link BCryptPasswordEncoder}
     * </p>
     *
     * @return {@link BCryptPasswordEncoder}
     *
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * <p>
     *   This method has the security filter that checks and authorize the user.
     * </p>
     *
     * @param httpSecurity {@link HttpSecurity} httpSecurity
     *      object required to authenticate and authorization
     * @return {@link SecurityFilterChain}
     * @throws Exception that occur while any one of the credentials
     *      given by user is invalid
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors().disable().csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

    /**
     * <p>
     *   Authenticates the passed object and returns {@link AuthenticationManager}
     * </p>
     *
     * @param authenticationConfiguration {@link AuthenticationConfiguration}
     *      configuration object that is used to get authentication manager
     * @return  {@link AuthenticationManager} returns authentication manager
     * @throws Exception when error occur in creating bean
     *
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * <p>
     *   This method creates authentication manager for the given UserDetailsService
     * </p>
     *
     * @param authenticationManagerBuilder {@link AuthenticationManagerBuilder}
     * @throws Exception when error occur in creating bean
     *
     */
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }
}
