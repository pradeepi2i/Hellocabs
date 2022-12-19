/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Range;

/**
 * <p>
 *   LoginDto class has the getters and setters meant to hold the
 *   login object consists of fields that are related to
 *   login and these values used to verify that the valid user logged
 *   into the application
 * </p>
 *
 * @author : Pradeep
 * created on 03/11/2022
 * @version 1.0
 *
 */
@Getter
@Setter
public class LoginDto {

    @NotNull(message = HelloCabsConstants.MOBILE_NUMBER_NOT_BLANK)
    @Range(min = 10, message = HelloCabsConstants.VALID_PHONE_NUMBER)
    private Long mobileNumber;

    @NotBlank(message = HelloCabsConstants.PASSWORD_REQUIRED)
    @Pattern(regexp = HelloCabsConstants.PASSWORD)
    private String password;
}
