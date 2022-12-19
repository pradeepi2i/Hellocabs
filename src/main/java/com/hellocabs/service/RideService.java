/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service;

import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.ReasonDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.model.Ride;

import java.util.Set;

/**
 * <p>
 *   Interface that contains abstract and static methods
 *   create, search, update and delete a ride
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public interface RideService {

    /**
     * <p>
     *   An abstract method to fetch a particular ride using id
     *   if ride exists return ride else return a new ride object
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link RideDto} searched ride object
     *
     */
    RideDto searchRideById(Integer id);

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride else throw not found exception
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link Ride} searched ride object
     *
     */
    Ride fetchRideById(Integer id);

    /**
     * <p>
     *   An abstract method to retrieve all rides
     *   irrespective of id
     *   Returns empty set when no rides available
     * </p>
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    Set<RideDto> retrieveRides();

    /**
     * <p>
     *   An abstract method to update particular ride
     *   First update the required fields also enter the id
     *   then only existing object updated else if no id mentioned
     *   a new object will be created
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link String} updated ride
     *
     */
    RideDto updateRide(RideDto rideDto);

    /**
     * <p>
     *   An abstract method whenever a user wants to cancel
     *   the ride for some reason, also get the reason for cancel
     *   the ride from user
     * </p>
     *
     * @param rideId {@link Integer} update rating for the ride
     * @param feedBackDto {@link ReasonDto} feedback and ride
     *                                       status details
     * @return {@link String} ride cancellation
     *
     */
    String deleteRide(Integer rideId, ReasonDto feedBackDto);

    /**
     * <p>
     *   An abstract method which is used when ride is booked, no
     *   cab categories were found on that location or
     *   no cab driver was accept this ride for
     *   some time, ride will be cancelled automatically
     *   and shows user a message to choose another cab category
     * </p>
     *
     * @param rideId {@link Integer}
     * @return rideDto {@link RideDto} updated rideDto
     *
     */
    String waitingToConfirmRide(Integer rideId);

    /**
     * <p>
     *   An abstract method which is often used to change the status
     *   of both ride and cab by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} status to be changed for the ride id
     * @return {@link CabDto} assigned cab details
     */
    CabDto updateRideStatus(StatusDto statusDto, Integer rideId);

    /**
     * <p>
     *   An abstract method, in which whenever a customer
     *   books a ride and choose category by using category
     *   id and also ride details like locations, time, etc.,
     *   It show locations and category details from which
     *   customer have to select location and category and
     *   then the ride is created also ride status changed
     *   to booked
     * </p>
     *
     * @param bookDto {@link BookDto} ride details of a customer
     * @return  {@link RideDto} booking id
     *
     */
    RideDto bookRide(BookDto bookDto);

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param rideId {@link Integer} update rating for the ride
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @return {@link RideDto} ride's feedback
     *
     */
    RideDto  submitFeedBack(Integer rideId, RatingDto ratingDto);

    /**
     * <p>
     *   This method often used to assign a ride to cab
     *   by passing the user input as an object and respective id
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be confirmed
     * @param cabId {@link Integer} cabId to be assigned to pick the ride
     * @return {@link RideDto} ride confirmation
     *
     */
    String confirmRide(StatusDto statusDto, Integer rideId, Integer cabId);
}
