/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.ReasonDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.RideService;

import java.util.Set;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

/**
 * <p>
 *   Implemented this class whenever a customer books a ride,
 *   to fetch particular ride, to show all ride history,
 *   to delete a ride while booking is not complete etc.,
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("rides")
public class RideController {

    private final RideService rideService;

    /**
     * <p>
     *   Used this method to fetch a particular ride
     *   by passing ride id to service
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link ResponseEntity<RideDto>} searched ride object
     *
     */
    @GetMapping("{id}")
    private ResponseEntity<?> searchRideById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.RIDE_FOUND,
                HttpStatus.OK,rideService.searchRideById(id));
    }

    /**
     * <p>
     *   Used this method to retrieve all rides
     *   irrespective of id
     * </p>
     *
     * @return {@link ResponseEntity<Set>} set of all rides
     *
     */
    @GetMapping
    private ResponseEntity<?> retrieveRides() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.RIDE_FOUND,
                HttpStatus.FOUND, rideService.retrieveRides());
    }

    /**
     * <p>
     *   Used this method to update particular ride
     *   by inserting the values of fields and id
     *   to be updated
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link ResponseEntity<RideDto>} updated ride
     *
     */
    @PutMapping
    private ResponseEntity<?> updateRide(@Valid @RequestBody RideDto rideDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.RIDE_UPDATED,
                HttpStatus.OK, rideDto);
    }

    /**
     * <p>
     *   Used this method whenever a customer books a ride
     *   and choose category by using category id and also
     *   ride details
     * </p>
     *
     * @param bookDto {@link BookDto} ride details of a customer
     * @return {@link ResponseEntity<RideDto>} booking id
     *
     */
    @PostMapping
    private ResponseEntity<?> bookRide(@Valid @RequestBody BookDto bookDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.RIDE_CREATED,
                HttpStatus.CREATED, rideService.bookRide(bookDto));
    }

    /**
     * <p>
     *   This method is often used to change the status of both ride and cab
     *   by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be updated
     * @return {@link ResponseEntity<RideDto>} assigned cab details
     *
     */
    @PutMapping("status/{rideId}")
    private ResponseEntity<?> updateRideStatus(@Valid @RequestBody StatusDto statusDto,
                                                    @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.STATUS_UPDATED,
                HttpStatus.OK, rideService.updateRideStatus(statusDto, rideId));
    }

    /**
     * <p>
     *   This method is often used to assign a ride to cab
     *   by passing the user input as an object and respective id
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be confirmed
     * @param cabId {@link Integer} cabId to be assigned to pick the ride
     * @return {@link ResponseEntity<String>} ride confirmation
     *
     */
    @PutMapping("confirm/{rideId}/cab/{cabId}")
    private ResponseEntity<?> confirmRide(@Valid
            @RequestBody StatusDto statusDto, @PathVariable Integer rideId,
            @PathVariable Integer cabId) {
        return HelloCabsResponseHandler.generateResponse(
                rideService.confirmRide(statusDto, rideId, cabId), HttpStatus.OK);
    }

    /**
     * <p>
     *   This method is used to confirm the ride is booked
     *   if not accepted by cab for Long time, ride will be
     *   cancelled automatically and shows user a message
     *   to choose another cab category
     * </p>
     *
     * @param rideId {@link Integer}
     * @return {@link ResponseEntity<String>} statement on
     *         ride confirmation
     *
     */
    @GetMapping("confirmationStatus/{rideId}")
    private ResponseEntity<?> waitingToConfirmRide(@PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(rideService.waitingToConfirmRide(rideId),
                HttpStatus.OK);
    }

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @param rideId {@link Integer} rating to be updated to ride
     * @return {@link ResponseEntity<RideDto>} ride's rating
     *
     */
    @PutMapping("rating/{rideId}")
    private ResponseEntity<?> submitFeedback(@Valid @RequestBody
            RatingDto ratingDto, @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.FEEDBACK_ADDED,
                HttpStatus.OK, rideService.submitFeedBack(rideId, ratingDto));
    }

    /**
     * <p>
     *   Used this method whenever a user wants to cancel
     *   the ride for some reason
     * </p>
     *
     * @param feedBackDto {@link ReasonDto} feedback of the ride
     * @param rideId {@link Integer} update rating for the ride
     * @return {@link ResponseEntity<Object>} ride cancellation
     *
     */
    @DeleteMapping("{rideId}")
    private ResponseEntity<?> deleteRideById(@Valid @RequestBody
            ReasonDto feedBackDto, @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(
                rideService.deleteRide(rideId, feedBackDto),
                HttpStatus.OK);
    }
}
