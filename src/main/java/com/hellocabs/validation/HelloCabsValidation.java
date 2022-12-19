/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.validation;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.exception.HelloCabsException;

/**
 * <p>
 *   This Class is used to validate user data before sent to database
 *   inorder to avoid wrong entry published in database
 * </p>
 *
 * @author : Pradeep
 * created on 4/11/2022
 * @version 1.0
 *
 */
public class HelloCabsValidation {

    /**
     * <p>
     *   Used this method to validate mobile number which is
     *   given by user if enters valid input then returns true
     *   else throws an exception
     * </p>
     *
     * @param mobileNumber {@link Long} mobile number to be validated
     * @return {@link Boolean} if valid returns true else throws exception
     *
     */
    public static boolean isValidMobileNumber(Long mobileNumber) {

        if (mobileNumber.toString().matches(HelloCabsConstants.MOBILE_NUMBER)) {
            return true;
        }
        throw new HelloCabsException(HelloCabsConstants.INVALID_MOBILE_NUMBER);
    }
}
