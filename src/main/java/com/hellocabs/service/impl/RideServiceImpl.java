/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.ReasonDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.enumeration.Status;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.mapper.CabMapper;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.CabService;
import com.hellocabs.service.RideService;

import com.hellocabs.util.HelloCabsUtil;
import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *   Class that provides implementation to all abstract
 *   methods that are available on ride service interface
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final CabService cabService;
    private final Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.RIDE_SERVICE_CLASS);

    /**
     * <p>
     *   Implement this method to get the Dto object
     *   and convert to entity object through mapper class
     *   then send the entity to repository layer to store ride
     *   in database
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be created
     * @return {@link Ride} created ride if created
     *
     */
    private Ride createRide(RideDto rideDto) {
        Ride ride = rideRepository.save(RideMapper
                .convertRideDtoIntoRide(rideDto));
        logger.info(HelloCabsConstants.RIDE_CREATED + ride.getId());
        return ride;
    }

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride by converting ride entity into dto
     *   object through mapper class else throw not found exception
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link RideDto} searched ride object else
     *      throw ride not found exception
     *
     */
    public RideDto searchRideById(Integer id) {
        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            logger.info(HelloCabsConstants.RIDE_FOUND + ride);
            return RideMapper.convertRideIntoRideDto(ride);
        }
        logger.error(HelloCabsConstants.RIDE_NOT_FOUND);
        /*throws exception when particular id is not found*/
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_FOUND);
    }

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride else throw not found exception
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link Ride} searched ride object else
     *      throw ride not found exception
     *
     */
    public Ride fetchRideById(Integer id) {
        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            logger.info(HelloCabsConstants.RIDE_FOUND + ride);
            return ride;
        }
        logger.error(HelloCabsConstants.RIDE_NOT_FOUND);
        /*throws exception when particular id is not found*/
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_FOUND);
    }

    /**
     * <p>
     *   Implement this method to retrieve all rides irrespective of
     *   their id and passed into stream to convert those ride entity
     *   into set of ride dto object
     * </p>
     *
     * @return {@link Set<RideDto>} set of all rides
     *      if exists else returns empty set
     *
     */
    public Set<RideDto> retrieveRides() {
        return rideRepository.findAll().stream()
                .map(RideMapper :: convertRideIntoRideDto)
                .collect(Collectors.toSet());
    }

    /**
     * <p>
     *   Implement this method to update particular ride by 
     *   converting the dto into entity through mapper, update
     *   the required fields also enter the id then only
     *   existing object updated else if no id mentioned
     *   a new object will be created
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link RideDto} updated object by converting
     *      entity into dto object using ride mapper
     *
     */
    public RideDto updateRide(RideDto rideDto) {
        Ride ride = rideRepository.save(RideMapper
                .convertRideDtoIntoRide(rideDto));
        logger.info(HelloCabsConstants.RIDE_UPDATED + ride);
        return RideMapper.convertRideIntoRideDto(ride);
    }

    /**
     * <p>
     *   Implement this method whenever a user wants to cancel
     *   the ride for some reason, also get the reason for cancel
     *   the ride from user
     * </p>
     *
     * @param rideId {@link Integer} feedback to be updated to ride
     * @param reasonDto {@link ReasonDto} feedback and ride status details
     * @return {@link String} reason for ride cancellation only id cancelled
     *      else throw exception stated that the reason for not cancel the ride
     *
     */
    public String deleteRide(Integer rideId, ReasonDto reasonDto) {
        Ride ride = fetchRideById(rideId);

        /*allows ride cancellation if and only if ride status is not dropped*/
        if (!Status.DROPPED.toString().equalsIgnoreCase(ride.getRideStatus())) {

            /*allows ride cancellation if and only if ride is not cancelled already*/
            if (!Status.CANCELLED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                ride.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
                ride.setFeedback(reasonDto.getReason());
                rideRepository.save(ride);
                logger.info(Status.CANCELLED);
                return HelloCabsConstants.RIDE_CANCELLED;
            }
            /*throws exception whenever, already cancelled ride request to cancel again*/
            throw new HelloCabsException(HelloCabsConstants.RIDE_CANCELLED_ALREADY);
        }
        /*throws exception if try to cancel a dropped ride*/
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_CANCELLED);
    }

    /**
     * <p>
     *   Implement this method whenever a customer books a ride
     *   and choose category by using category id and also
     *   ride details like locations, time, etc.,
     *   It show locations and category details from which
     *   customer have to select location and category and
     *   then the ride is created also ride status changed
     *   to booked
     * </p>
     *
     * @param bookDto {@link BookDto} ride details of a customer
     * @return {@link RideDto} booking id
     *
     */
    public RideDto bookRide(BookDto bookDto) {
        RideDto rideDto = new RideDto();
        rideDto.setPassengerMobileNumber(bookDto.getPassengerMobileNumber());
        rideDto.setPickupLocation(new LocationDto(bookDto.getPickupLocation()));
        rideDto.setDropLocation(new LocationDto(bookDto.getDropLocation()));
        rideDto.setCustomerDto(new CustomerDto((bookDto.getCustomerId())));
        rideDto.setRideStatus(HelloCabsConstants.RIDE_BOOKED);
        rideDto.setRideBookedTime(LocalDateTime.now());
        return RideMapper.convertRideIntoRideDto(createRide(rideDto));
    }

    /**
     * <p>
     *   Implement this method often used to assign a ride to cab
     *   by passing the user input as an object and respective id
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be confirmed
     * @param cabId {@link Integer} cabId to be assigned to pick the ride
     * @return {@link String} ride confirmation
     *
     */
    public String confirmRide(StatusDto statusDto, Integer rideId, Integer cabId) {
        Ride ride = fetchRideById(rideId);
        Cab cab = cabService.searchCabById(cabId);

        /*Check if status is accepted and also the accepted cab's status should be available*/
        if (Status.ACCEPTED.toString().equalsIgnoreCase(statusDto.getRideStatus())
                && Status.AVAILABLE.toString().equalsIgnoreCase(cab.getCabStatus())) {

            /*Check if status is booked*/
            if ((Status.BOOKED.toString().equalsIgnoreCase(ride.getRideStatus()))) {
                ride.setRideStatus(statusDto.getRideStatus());
                ride.setCab(cab);
                cab.setCabStatus(HelloCabsConstants.CAB_UNAVAILABLE);
                rideRepository.save(ride);
                cabService.updateCabDetailsById(cabId, cab);
                return HelloCabsConstants.CAB_ASSIGNED;
            }
            /*throws exception if ride status rather than booked*/
            throw new HelloCabsException(HelloCabsConstants.RIDE_ACCEPTED_ALREADY);
        }
        /*throws exception stated that ride not accepted*/
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_ACCEPTED);
    }

    /**
     * <p>
     *   Implement this method is often used to change the status of both
     *   ride and cab by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @return {@link CabDto} assigned cab details
     *
     */
    public CabDto updateRideStatus(StatusDto statusDto, Integer rideId) {
        Ride ride = fetchRideById(rideId);
        logger.info(HelloCabsConstants.RIDE_FOUND);
        return updateStatusInfo(statusDto, ride);
    }

    /**
     * <p>
     *   Implement this method is often used to change the status of both
     *   ride and cab by passing the rideStatus, and respective objects
     * </p>
     *
     * @param statusDto {@link StatusDto} status of ride
     * @param ride {@link Ride} change ride's ride status
     * @return {@link CabDto} updated cabDto
     *
     */
    private CabDto updateStatusInfo(StatusDto statusDto, Ride ride) {
        final String PICKED = "picked";
        final String DROPPED = "dropped";
        String rideStatus = statusDto.getRideStatus();
        Cab cab = ride.getCab();

        switch (rideStatus.toLowerCase()) {
            case PICKED:
                /*Check if status is booked*/
                if (Status.ACCEPTED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    ride.setRideStatus(statusDto.getRideStatus());
                    ride.setRidePickedTime(LocalDateTime.now());
                    ride.setRideStatus(rideStatus);
                    cab.setCabStatus(HelloCabsConstants.CAB_ON_RIDE);
                } else if (Status.PICKED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    /*throws exception stated that ride picked already accepted*/
                    throw new HelloCabsException(HelloCabsConstants.RIDE_PICKED_ALREADY);
                } else {
                    /*throws exception stated that ride not accepted*/
                    throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_ACCEPTED);
                }
                break;

            case DROPPED:
                /*Check if status is booked*/
                if (Status.PICKED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    ride.setRideDroppedTime(statusDto.getDropTime());

                    if (null == ride.getRideDroppedTime()) {
                        ride.setRideDroppedTime(LocalDateTime.now());
                    }
                    ride.setRideStatus(statusDto.getRideStatus());
                    cab.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                    logger.info(ride.getDropLocation());
                    cab.setCurrentLocation(ride.getDropLocation().getLocationName());
                    ride.setPrice(HelloCabsUtil.calculateTravelFare(ride, cab.getCabCategory()));
                } else {
                    /*throws exception stated that customer is not picked yet*/
                    throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_PICKED);
                }
                break;

            default:
                /*throws exception when the given status is invalid*/
                throw new HelloCabsException(HelloCabsConstants.STATUS_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.STATUS_UPDATED + rideStatus);
        rideRepository.save(ride);
        return CabMapper.convertCabToCabDto(cabService.updateCabDetailsById(cab.getId(), cab));
    }

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param rideId {@link Integer} rating for the ride
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @return {@link RideDto} ride's feedback
     *
     */
    public RideDto submitFeedBack(Integer rideId, RatingDto ratingDto) {
        Ride ride = fetchRideById(rideId);
        Cab cab = ride.getCab();

        /*Only ride which is dropped is able to give feedback*/
        if (Status.DROPPED.toString().equalsIgnoreCase(ride.getRideStatus())) {
            ride.setRating(ratingDto.getRating());
            ride.setFeedback(ratingDto.getFeedback());
            cabService.updateCabDetailsById(cab.getId(), (
                    HelloCabsUtil.calculateAverageRating(cab, ratingDto)));
            return RideMapper.convertRideIntoRideDto(rideRepository.save(ride));
        }
        /*throws exception stated that customer is not dropped yet*/
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
    }

    /**
     * <p>
     *   Implement this method is used when ride is booked, no
     *   cab categories were found on that location or
     *   no cab driver was accept this ride for
     *   some time, ride will be cancelled automatically
     *   and shows user a message like try again
     * </p>
     *
     * @param rideId {@link Integer}
     * @return ride {@link String} updated rideDto
     *
     */
    public String waitingToConfirmRide(Integer rideId) {
        Ride ride = fetchRideById(rideId);

        /*Ride which is booked already can only check the status confirmation*/
        if (HelloCabsConstants.RIDE_BOOKED
                .equalsIgnoreCase(ride.getRideStatus())) {

            if ((HelloCabsConstants.MAXIMUM_WAITING_TIME < (LocalDateTime.now().getMinute())
                    - ride.getRideBookedTime().getMinute()) && (HelloCabsConstants.RIDE_BOOKED)
                    .equalsIgnoreCase(ride.getRideStatus())) {
                ride.setFeedback(HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY);
                ride.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
                rideRepository.save(ride);
                return HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY;
            }
            logger.info(HelloCabsConstants.SEARCHING_CABS);
            return HelloCabsConstants.SEARCHING_CABS;
        }
        /*throws exception that the ride is passed the booked state*/
        throw new HelloCabsException(HelloCabsConstants.ALREADY_ON_BOARD);
    }
}