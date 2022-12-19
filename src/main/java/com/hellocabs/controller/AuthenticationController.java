/*
 *  Copyright (c) All rights reserved Ideas2IT
 */

package com.hellocabs.controller;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LoginDto;
import com.hellocabs.exception.HelloCabsException;

import com.hellocabs.response.HelloCabsResponseHandler;
import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h> AuthenticationController </h>
 * <p>
 *   The class authenticates whether the credentials
 *   entered by user in login is present or not in database
 * </p>
 *
 * @author Gautam
 * @version 1.0.
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final Logger logger = LoggerConfiguration.getInstance(
            HelloCabsConstants.AUTHENTICATION_CONTROLLER);
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    /**
     * <p>
     *   This method authenticate the user for the provided credentials whether the
     *   user already exists with the provided user name and password the account
     *   will be permitted otherwise throws badCredentialsException
     *   {@link BadCredentialsException}.
     * </p>
     *
     * @param loginDto {@link LoginDto}loginDto contains mobileNumber and password.
     * @return {@link ResponseEntity} returns login status.
     *
     */
    @PostMapping("/login")
    private ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(Long
                    .toString(loginDto.getMobileNumber()), loginDto.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            logger.error(HelloCabsConstants.INVALID_USERNAME_OR_PASSWORD);
            throw new HelloCabsException(new BadCredentialsException(HelloCabsConstants
                    .INVALID_USERNAME_OR_PASSWORD).getMessage());
        }
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(Long.toString(loginDto.getMobileNumber()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info(HelloCabsConstants.LOGIN_SUCCESSFUL);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOGIN_SUCCESSFUL,
                HttpStatus.OK);
    }
}
