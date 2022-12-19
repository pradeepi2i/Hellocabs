/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hellocabs.constants.HelloCabsConstants;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.List;

/**
 * <p>
 *   CabCategoryDto class has the getters and setters meant to hold the
 *   cab category dto object consists the fields that are related to
 *   cab category and it is used to transfer data between user
 *   and database.
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Data
public class CabCategoryDto {

    private Integer id;

    @NotBlank(message = HelloCabsConstants.CAB_TYPE_NOT_BLANK)
    @Pattern(regexp = (HelloCabsConstants.CAB_TYPE_REGEX), message = HelloCabsConstants.ENTER_VALID_CAB_TYPE)
    private String cabType;

    @DecimalMin(value = HelloCabsConstants.DECIMAL_MIN, message = HelloCabsConstants.INVALID_FARE)
    private Double initialFare;

    @DecimalMin(value = HelloCabsConstants.DECIMAL_MIN, message = HelloCabsConstants.INVALID_FARE)
    private Double extraFarePerHour;

    @DecimalMin(value = HelloCabsConstants.DECIMAL_MIN, message = HelloCabsConstants.INVALID_FARE)
    private Double peakHourFare;

    @JsonIgnore
    private List<CabDto> cabs;
}
