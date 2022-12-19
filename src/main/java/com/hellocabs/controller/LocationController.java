/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.LocationService;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * Location controller class has the methods that handles
 * CRUD operations of location.
 * </p>
 *
 * @author  Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    /**
     * <p>
     *   This method is to add location Details.
     * </p>
     *
     * @param locationDto {@link LocationDto} for which the location to be added is given
     * @return {@link ResponseEntity<LocationDto>} returned created location
     */
    @PostMapping
    private ResponseEntity<?> addLocation(@Valid @RequestBody LocationDto locationDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_CREATED,
                HttpStatus.CREATED, locationService.createLocation(locationDto));
    }

    /**
     * <p>
     *   This method is to search location Details.
     * </p>
     *
     * @param id {@link Integer} for which the id of the location need to
     *        be searched is given
     * @return {@link ResponseEntity<LocationDto>} searched location is returned
     *
     */
    @GetMapping("{id}")
    private ResponseEntity<?> searchLocationById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .LOCATION_FOUND, HttpStatus.FOUND, locationService.getLocationById(id));
    }

    /**
     * <p>
     *   This method is to update location Details.
     * </p>
     *
     * @param locationDto {@link LocationDto} for which the location to be updated is given
     * @return {@link ResponseEntity<LocationDto>} updated location is returned
     *
     */
    @PutMapping
    private ResponseEntity<?> updateLocation( @Valid @RequestBody LocationDto locationDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                    .LOCATION_UPDATED, HttpStatus.OK, locationService.updateLocation(locationDto));
    }

    /**
     * <p>
     *   This method is to update location Details.
     * </p>
     *
     * @param locationDto {@link LocationDto} for which the location to be updated is given
     * @param id {@link Integer} id to be updated
     * @return {@link ResponseEntity<LocationDto>} updated location is returned
     *
     */
    @PatchMapping
    private ResponseEntity<?> updateLocationById( @Valid @RequestBody
            LocationDto locationDto, @PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .LOCATION_UPDATED, HttpStatus.OK, locationService.updateLocation(locationDto));
    }

    /**
     * <p>
     * This method is to delete location Details.
     * </p>
     *
     * @param id {@link Integer} for which the id of the location need to
     *        be deleted is given
     * @return {@link ResponseEntity<String>} gets the message whether the location is
     *         deleted or not
     *
     */
    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteLocationById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(locationService.deleteLocationById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is to display all location Details.
     * </p>
     *
     * @return {@link ResponseEntity<List>} retrieves all the locations
     *
     */
    @GetMapping
    private ResponseEntity<?> getAllLocations() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_FOUND,
                HttpStatus.OK, locationService.retrieveAllLocations());
    }
}
