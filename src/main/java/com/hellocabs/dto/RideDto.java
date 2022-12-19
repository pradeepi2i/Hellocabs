/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellocabs.constants.HelloCabsConstants;

import lombok.Getter;
import lombok.Setter;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;

/**
 * <p>
 *   A Dto object which is responsible for collect and display
 *   all possible information regarding ride from user through
 *   controller and further convert to Entity object through
 *   service using mapper classes
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Getter
@Setter
@Validated
public class RideDto {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideBookedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ridePickedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideDroppedTime;

    private Double price;

    private Long passengerMobileNumber;

    private Double rating;

    private String feedback;

    @NotBlank(message = HelloCabsConstants.RIDE_STATUS_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.RIDE_STATUS_REGEX,
            message = HelloCabsConstants.RIDE_STATUS)
    private String rideStatus;

    @JsonIgnore
    private boolean isCancelled;

    private LocationDto pickupLocation;

    private LocationDto  dropLocation;

    private CabDto cabDto;

    private CustomerDto customerDto;
}

