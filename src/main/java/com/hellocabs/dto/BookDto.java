/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;

import lombok.Data;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *   A Dto object which is responsible for collect and display
 *   all possible information from user while booking a ride
 * </p>
 *
 * @author : Pradeep
 * created on 31/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class BookDto {

    private Long passengerMobileNumber;

    @NotNull(message = HelloCabsConstants.LOCATION_NOT_BLANK)
    private Integer pickupLocation;

    @NotNull(message = HelloCabsConstants.LOCATION_NOT_BLANK)
    private Integer dropLocation;

    @NotNull(message = HelloCabsConstants.CUSTOMER_ID_NOT_BLANK)
    private Integer customerId;

}
