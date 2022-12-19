/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;

import lombok.Data;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

/**
 * <p>
 *   A Dto object which is responsible for collect feedback
 *   and rating after a ride is completed
 * </p>
 *
 * @author : Pradeep
 * created on 31/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class RatingDto {

    private Double rating;

    @Size(min = 3, message = HelloCabsConstants.FIELD_NOT_BLANK)
    private String feedback;
}
