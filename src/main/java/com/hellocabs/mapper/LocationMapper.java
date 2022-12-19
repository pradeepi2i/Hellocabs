/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *   LocationMapper class consists of the functions that converts
 *   dto of the location object to entity and vice versa which is
 *   used during the normal operation of the hello cabs application.
 * </p>
 *
 * @author Divya S
 * @version 1.0 Oct-29-2022
 *
 */
public class LocationMapper {

    /**
     * <p>
     *   This method is used to convert location entity into location dto
     *   after the application gets input from user
     * </p>
     *
     * @param location {@link Location} entity object to be converted
     * @return {@link LocationDto} converted dto object
     *
     */
    public static LocationDto locationToLocationDto(Location location) {
        return MapperConfig.getConfigure().map(location, LocationDto.class);
    }

    /**
     * <p>
     *   This method is used to convert location dto into location entity
     *   after the application sends output from database
     * </p>
     *
     * @param locationDto {@link LocationDto} dto object to be converted
     * @return {@link Location} converted entity object
     *
     */
    public static Location locationDtoToLocation(LocationDto locationDto) {
        return MapperConfig.getConfigure().map(locationDto, Location.class);
    }

    /**
     * <p>
     *   This method is used to convert list of locations into list of location dto
     *   after the application sends list of locations as output from database
     * </p>
     *
     * @param locations {@link Location} dto object to be converted
     * @return {@link List<LocationDto>} converted list of dto object
     *
     */
    public static List<LocationDto> locationsToLocationDtos(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<>();

        /*Iterate list of location and convert those locations into list of locationDto*/
        for (Location location: locations) {
            locationDtos.add(locationToLocationDto(location));
        }
        return locationDtos;
    }
}
