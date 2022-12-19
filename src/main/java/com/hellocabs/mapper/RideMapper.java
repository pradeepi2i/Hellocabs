/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Ride;

/**
 * <p>
 *   Class that used to convert the user input(dto) object to entity object
 *   and vice versa
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public class RideMapper {

    /**
     * <p>
     *   Used this method to convert rideDto into ride entity
     *   when get input from user and store in database
     * </p>
     *
     * @param rideDto {@link RideDto} dto object to be converted
     * @return ride {@link Ride} converted object
     *
     */
    public static Ride convertRideDtoIntoRide(RideDto rideDto) {
        return MapperConfig.getConfigure().map(rideDto, Ride.class);
    }

    /**
     * <p>
     *   Used this method to convert ride entity into rideDto
     *   when to fetch data from database to show output to user
     * </p>
     *
     * @param ride {@link Ride} entity to be converted
     * @return rideDto {@link RideDto} converted object
     *
     */
    public static RideDto convertRideIntoRideDto(Ride ride) {
        return MapperConfig.getConfigure().map(ride, RideDto.class);
    }
}
