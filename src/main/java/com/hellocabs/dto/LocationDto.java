/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hellocabs.constants.HelloCabsConstants;
import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 *   LocationDto class has the getters and setters meant to hold the
 *   location dto object consists the fields that are related to
 *   location and it is used to transfer data between user
 *   and database.
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationDto {

    public LocationDto(Integer id) {
        this.id = id;
    }

    private Integer id;

    private String landmark;

    @NotBlank(message = HelloCabsConstants.LOCATION_NOT_BLANK)
    @Size(min = HelloCabsConstants.NAME_SIZE_MIN, message = HelloCabsConstants.LOCATION_NAME_SIZE)
    @Pattern(regexp = (HelloCabsConstants.LOCATION_NAME_REGEX), message = HelloCabsConstants.INVALID_LOCATION_NAME)
    private String locationName;

    private String addressType;

    private Integer postalCode;

    @JsonIgnore
    private boolean isDeleted;


}
