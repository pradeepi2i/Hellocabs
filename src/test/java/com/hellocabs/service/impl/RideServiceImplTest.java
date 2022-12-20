package com.hellocabs.service.impl;

import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Location;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RideServiceImplTest {

    @Mock
    RideRepository rideRepository;
    Ride ride;

    @Mock
    CabServiceImplTest cabServiceImplTest;

    public static Integer id() {
        return 10;
    }

    @BeforeEach
    void setup() {
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

        //cabServiceImplTest = new CabServiceImplTest();
    }

    @Test
    void bookRide() {
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals(ride, rideRepository.save(ride));
    }

    @Test
    void getRideSuccess() {
        when(rideRepository.getById(1)).thenReturn(ride);
        assertEquals(1,rideRepository.getById(1).getId());
    }

    @Test
    void getRideFailure() {
        when(rideRepository.getById(0)).thenThrow(new HelloCabsException("Ride"));
        Throwable exception = assertThrows(HelloCabsException.class, () -> rideRepository.getById(0));
        assertEquals("Ride", exception.getMessage());
    }

    @Test
    void getAllRidesSuccess() {
        when(rideRepository.findAll()).thenReturn(Stream.of(ride, ride).collect(Collectors.toList()));
        assertEquals(2, rideRepository.findAll().size());
    }

    @Test
    void updateRideSuccess() {
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals(ride, rideRepository.save(ride));
    }

    @Test
    void UpdateRideNonExistedId() {
        Ride anotherRide = new Ride();
        anotherRide.setId(2);
        when(rideRepository.save(ride)).thenReturn(ride);
        assertNotEquals(ride, rideRepository.save(anotherRide), "No Id found");
    }

    @Test
    void updateRideStatusSuccess() {
        ride.setRideStatus("Accepted");
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals("Accepted", rideRepository.save(ride).getRideStatus());
        assertNotEquals(ride, null);
       // assertEquals(null, doThrow(new NoSuchElementException("No such id found")));
    }


    @Test
    void updateRideStatusEmptyString() {
        ride.setRideStatus("");
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals("", rideRepository.save(ride).getRideStatus());
    }

    @Test
    void updateRideStatusNull() {
        ride.setRideStatus(null);
        when(rideRepository.save(ride)).thenReturn(ride);
        assertEquals(null, rideRepository.save(ride).getRideStatus());
        //assertEquals(null, doThrow(new NoSuchElementException("Can't assign null to status")));
    }


    @Test
    void deleteRide() {
        rideRepository.deleteById(1);
        verify(rideRepository, times(1)).deleteById(1);
    }

    @Test
    void confirmRide() {
        getRideSuccess();
        getRideFailure();
        cabServiceImplTest.getCabSuccess();
        cabServiceImplTest.getCabFailure();

    }

    @Test
    void updateStatusInfo() {
        updateRideStatusSuccess();
        updateRideStatusEmptyString();
        updateRideStatusNull();
        cabServiceImplTest.updateCabStatusSuccess();
        cabServiceImplTest.updateCabStatusEmptyString();
        cabServiceImplTest.updateCabStatusNull();
    }

    @Test
    void waitingToConfirmRideSuccess() {
        getRideSuccess();
        updateRideStatusSuccess();
    }

    @Test
    void waitingToConfirmRideFailure() {
        getRideSuccess();
        getRideFailure();
        updateRideStatusNull();
        updateRideStatusEmptyString();
    }
}
