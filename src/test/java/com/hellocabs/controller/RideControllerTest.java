/*
 * Copyright 2022 Netsmart Technologies, Inc. All rights reserved.
 * NETSMART PROPRIETARY/CONFIDENTIAL.
 */
package com.hellocabs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.ReasonDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private ObjectWriter objectWriter = objectMapper.writer();

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
    public void searchRideByIdSuccess() throws Exception {
        Mockito.when(rideService.searchRideById(1)).thenReturn(rideDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/rides/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.rideStatus", is("Cancelled")));
    }

    @Test
    @DisplayName("Retrieve all rides")
    public void retrieveRidesSuccess() throws Exception {
        Set<RideDto> rideDtos = new HashSet<>();
        rideDtos.add(rideDto);
        rideDtos.add(new RideDto());
        rideDtos.add(new RideDto());
        Mockito.when(rideService.retrieveRides()).thenReturn(rideDtos);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/rides")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().json("{}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()", is(3)));
    }

    @Test
    @DisplayName("Update a ride")
    public void  updateRideSuccess() throws Exception{
        rideDto.setRideStatus("Accepted");
        rideDto.setFeedback("Good");
        rideDto.setRating(4.5);
        rideDto.setPassengerMobileNumber(6383641462L);
        Mockito.when(rideService.updateRide(rideDto)).thenReturn(rideDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/rides")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(rideDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("Create a ride for user")
    public void bookRideSuccess() throws Exception{
        BookDto bookDto = BookDto.builder()
                .passengerMobileNumber(9876543210L)
                .customerId(1)
                .dropLocation(3)
                .pickupLocation(5)
                .build();
        Mockito.when(rideService.bookRide(bookDto)).thenReturn(rideDto);
        String content = objectWriter.writeValueAsString(bookDto);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/rides")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.rideStatus", is("Cancelled")));

    }

    @Test
    @DisplayName("Update status of a ride")
    public void updateRideStatusSuccess() throws Exception{
        StatusDto statusDto = StatusDto.builder()
                .rideStatus("Picked")
                .dropTime(LocalDateTime.now())
                .build();
        CabDto cabDto = new CabDto();
        Mockito.when(rideService.updateRideStatus(statusDto, 1)).thenReturn(cabDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/rides/status/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(statusDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("Ride confirmation: ride accepted by driver")
    public void confirmRideSuccess() throws Exception {
        StatusDto statusDto = StatusDto.builder()
                .rideStatus("Accepted")
                .dropTime(LocalDateTime.now())
                .build();
        Mockito.when(rideService.confirmRide(statusDto, 1,1)).thenReturn("Cab Assigned");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/rides/confirm/1/cab/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(statusDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", is("Cab Assigned")));
    }

    @Test
    @DisplayName("Waiting to be accepted by driver")
    public void waitingToConfirmRideSuccess() throws Exception{
        Mockito.when(rideService.waitingToConfirmRide(1)).thenReturn("Ride found");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rides/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", is("Ride found")));
    }

    @Test
    @DisplayName("Submit feedback for the ride")
    public void submitFeedbackSuccess() throws Exception {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setFeedback("Good and OverPaid");
        Mockito.when(rideService.submitFeedBack(1, ratingDto)).thenReturn(rideDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/rides/rating/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(ratingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", is("Thanks for giving valuable feedback")));
    }

    @Test
    @DisplayName("Delete/Cancel a ride")
    public void deleteRideSuccess() throws Exception {
        ReasonDto reasonDto = new ReasonDto();
        reasonDto.setReason("Too Long");
        Mockito.when(rideService.deleteRide(1, reasonDto)).thenReturn("Ride cancelled successfully");
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rides/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(reasonDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        is("Ride cancelled successfully")));
    }
}
