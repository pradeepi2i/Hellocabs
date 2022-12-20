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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RideServiceImplTest {

    @Mock
    RideRepository rideRepository;
    Ride ride;

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
        Ride ride1 = new Ride();
        ride1.setId(2);
        when(rideRepository.save(ride)).thenReturn(ride);
        assertNotEquals(ride, rideRepository.save(ride1), "No Id found");
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
    void deleteRide() {
        rideRepository.deleteById(1);
        verify(rideRepository, times(1)).deleteById(1);
    }
}
