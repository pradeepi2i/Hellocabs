/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service;

import com.hellocabs.dto.LocationDto;

import java.util.List;

/**
 * <h> LocationServiceImpl </h>
 * <p>
 *   Interface that contains abstract methods which are used
 *   to perform CRUD operations for Location object Details
 *   given by the user with help of locationDto object
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
public interface LocationService {
    /**
     * <p>
     *   This method is to add location Details by getting and converting the location
     *   dto into location entity and  saves into the location repository and returns
     *   message with created location id only if the location name is not already
     *   exits otherwise it throws exception.
     * </p>
     *
     * @param locationDto {@link LocationDto} for which the location to be added is given
     * @return {@link LocationDto} inserted location id is returned with message
     *
     */
    LocationDto createLocation(LocationDto locationDto);

    /**
     * <p>
     *   This method is to update location Details by getting and converting the location
     *   dto into location entity and saves into the location repository and returns
     *   updated location as dto only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param locationDto {@link LocationDto} for which the location to be updated is given
     * @return {@link LocationDto} updated location is returned
     *
     */
    LocationDto updateLocation(LocationDto locationDto);

    /**
     * <p>
     *   This method is to get location Details by getting location id
     *   and retrieves the location from the location repository and
     *   returns searched location as dto only if the given id is
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the location need to be searched is given
     * @return {@link LocationDto} searched location is returned
     *
     */
    LocationDto getLocationById(Integer id);

    /**
     * <p>
     *   This method is to delete location Details by getting the id to be deleted
     *   and deletes the location in location repository and returns the message
     *   only if the given id is already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the location need to be deleted is given
     * @return {@link String} returns the message if the location is deleted
     *
     */
    String deleteLocationById(Integer id);

    /**
     * <p>
     *   This method is to retrieve location Details and converts the location
     *   entity into location dto and returns the locations from the location
     *   repository
     * </p>
     *
     * @return {@link List<LocationDto>} retrieves all the locations
     *
     */
    List<LocationDto> retrieveAllLocations();
}
