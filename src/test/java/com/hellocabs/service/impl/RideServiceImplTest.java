/*
 * Copyright 2022 Netsmart Technologies, Inc. All rights reserved.
 * NETSMART PROPRIETARY/CONFIDENTIAL.
 */

package com.hellocabs.service.impl;

import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Location;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class with set of test functions which are used to test both
 * positive and negative case for an individual method
 *
 * @author : Pradeep
 * created on 21/12/2022
 * @version 1.0
 *
 */
@SpringBootTest
public class RideServiceImplTest {

    @Mock
    private RideRepository rideRepository;
    private Ride ride;
    private Ride savedRide;
    @Mock
    private CabServiceImplTest cabServiceImplTest;


    @BeforeEach
    public void setup() {
        ride = new Ride();
        ride.setId(1);
        Location pickupLocation = new Location();
        pickupLocation.setId(2);
        ride.setPickupLocation(pickupLocation);
        Location dropLocation = new Location();
        dropLocation.setId(6);
        ride.setDropLocation(dropLocation);
        ride.setRideBookedTime(LocalDateTime.parse("2022-11-03T19:21:57"));
        ride.setRidePickedTime(LocalDateTime.parse("2022-11-05T13:58:29"));
        ride.setPassengerMobileNumber(Long.valueOf("9876543212"));
        ride.setRideStatus("Cancelled");
    }

    /**
     * Used this method to check that the given input and the returned
     * object are equal if they are not then the testcase will fail
     */
    @Test
    @DisplayName("Check the given object and saved object are equal")
    public void bookRide() {
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals(ride, rideRepository.save(ride), "Both are same");
    }

    /**
     * Test whether the given id has been on repository
     * only then the test case should succeed
     */
    @Test
    @DisplayName("Fetch Success:Get ride using id")
    public void getRideSuccess() {
        when(rideRepository.getById(1)).thenReturn(ride);
        savedRide = rideRepository.getById(1);
        assertEquals(1, savedRide.getId());
    }

    /**
     * Test whether the id is a valid one or not, if not then throws exception
     * {@link HelloCabsException}
     */
    @Test
    @DisplayName("Fetch Failure: Due to invalid id")
    public void getRideFailure() {
        when(rideRepository.getById(0)).thenThrow(new HelloCabsException("Invalid Id"));
        Throwable exception = assertThrows(HelloCabsException.class, () -> rideRepository.getById(0));
        assertEquals("Ride", exception.getMessage());
    }

    /**
     * The number of ride object in the list and no.of object in the
     * repository must be equal then only test case pass
     */
    @Test
    @DisplayName("Fetch All rides Success")
    public void getAllRidesSuccess() {
        when(rideRepository.findAll()).thenReturn(Stream.of(ride, ride).collect(Collectors.toList()));
        assertEquals(2, rideRepository.findAll().size());
    }

    /**
     * Update the ride details only when the ride id is valid and succeed the test case
     */
    @Test
    @DisplayName("Update Ride Success")
    public void updateRideSuccess() {
        when(rideRepository.save(ride)).thenReturn(ride);
        savedRide = rideRepository.save(ride);
        assertEquals(ride, savedRide );
    }

    /**
     * Test case succeeds if the user try to update the details
     * of the ride which is not existed in database
     */
    @Test
    @DisplayName("Update Ride Failure : Ride update failed due to non-existed id")
    public void UpdateRideNonExistedId() {
        Ride anotherRide = new Ride();
        anotherRide.setId(2);
        when(rideRepository.save(ride)).thenReturn(ride);
        savedRide = rideRepository.save(anotherRide);
        assertNotEquals(ride, savedRide, "No Id found");
    }

    /**
     * Update the status info of the ride only when the ride status
     * is a valid one and fails if an entered status value is invalid
     */
    @Test
    @DisplayName("Update status Success")
    public void updateRideStatusSuccess() {
        ride.setRideStatus("Accepted");
        when(rideRepository.save(ride)).thenReturn(ride);
        savedRide = rideRepository.save(ride);
        assertEquals("Accepted", savedRide.getRideStatus());
        assertNotEquals(ride, null);
    }

    /**
     * When user passed a empty value to update the status of ride
     */
    @Test
    @DisplayName("Update status Failure : Status is empty")
    public void updateRideStatusEmptyString() {
        ride.setRideStatus("");
        when(rideRepository.save(ride)).thenReturn(ride);
        savedRide = rideRepository.save(ride);
        assertEquals("", savedRide.getRideStatus());
    }

    /**
     * When user passed a null value to update the status of ride     *
     */
    @Test
    @DisplayName("Update status Failure : null is passed as a status")
    public void updateRideStatusNull() {
        ride.setRideStatus(null);
        when(rideRepository.save(ride)).thenReturn(ride);
        savedRide = rideRepository.save(ride);
        assertEquals(null, savedRide.getRideStatus());
    }


    /**
     * whenever the user wants to cancel the ride due to some reason
     * and make sure that the id is a valid one
     */
    @Test
    @DisplayName("Delete ride")
    public void deleteRide() {
        rideRepository.deleteById(1);
        verify(rideRepository, times(1)).deleteById(1);
    }

    /**
     * confirms the ride when the cab driver accepts
     * the ride request from the user
     */
    @Test
    @DisplayName("Ride Confirmation")
    public void confirmRide() {
        getRideSuccess();
        getRideFailure();
        cabServiceImplTest.getCabSuccess();
        cabServiceImplTest.getCabFailure();

    }

    /**
     * Test that all the status like ride status and cab status are valid one
     * and make sure that invalid data(status) results in test case failure
     */
    @Test
    @DisplayName("Update status of both cab and ride as per the scenario")
    public void updateStatusInfo() {
        updateRideStatusSuccess();
        updateRideStatusEmptyString();
        updateRideStatusNull();
        cabServiceImplTest.updateCabStatusSuccess();
        cabServiceImplTest.updateCabStatusEmptyString();
        cabServiceImplTest.updateCabStatusNull();
    }

    /**
     * Test case succeed that all the credentials like if user
     * gives a valid id and also valid status
     */
    @Test
    @DisplayName("Failure message: Confirmation failed")
    public void waitingToConfirmRideSuccess() {
        getRideSuccess();
        updateRideStatusSuccess();
    }

    /**
     * Test that all the credentials like if user
     * gives an invalid id and also status as null or empty string
     */
    @Test
    @DisplayName("Failure message: Confirmation failed")
    public void waitingToConfirmRideFailure() {
        getRideSuccess();
        getRideFailure();
        updateRideStatusNull();
        updateRideStatusEmptyString();
    }
}
