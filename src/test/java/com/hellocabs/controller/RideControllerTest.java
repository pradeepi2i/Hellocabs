package com.hellocabs.controller;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.service.RideService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RideControllerTest {

    private MockMvc mockMvc;
    @Mock
    private RideService rideService;
    @InjectMocks
    private RideController rideController;
    private RideDto rideDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();

        rideDto = new RideDto();
        rideDto.setId(1);
        LocationDto pickupLocation = new LocationDto();
        pickupLocation.setId(2);
        rideDto.setPickupLocation(pickupLocation);
        LocationDto dropLocation = new LocationDto();
        dropLocation.setId(6);
        rideDto.setDropLocation(dropLocation);
        rideDto.setRideBookedTime(LocalDateTime.parse("2022-11-03T19:21:57"));
        rideDto.setRidePickedTime(LocalDateTime.parse("2022-11-05T13:58:29"));
        rideDto.setPassengerMobileNumber(Long.valueOf("9876543212"));
        rideDto.setRideStatus("Cancelled");
    }

    @Test
    @DisplayName("Search particular ride using id")
    public void searchRideByIdSuccess() throws Exception{

        Mockito.when(rideService.searchRideById(1)).thenReturn(rideDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/rides/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
                //.andExpect(jsonPath("$.rideStatus", is));
    }

    @Test
    @DisplayName("Retrieve all rides")
    public void retrieveRidesSuccess() throws Exception {
        List<RideDto> rideDtos = new ArrayList<>();
        rideDtos.add(rideDto);
        rideDtos.add(new RideDto());
        rideDtos.add(new RideDto());
        Mockito.when(rideService.retrieveRides()).thenReturn((Set<RideDto>) rideDtos);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/rides")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].rideStatus", is("Cancelled")));
    }
}
