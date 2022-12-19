/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.constants;

/**
 * <p>
 *   Constants class contains number of constants available which is used
 *   throughout entire application
 * </p>
 *
 * @author : Divya
 * @version 1.0 Oct-28-2022
 *
 */
public class HelloCabsConstants {

    //LOGGER
    public static final String RIDE_SERVICE_CLASS = "RideServiceImpl";
    public static final String CAB_CATEGORY_SERVICE = "CabCategoryService";
    public static final String LOCATION_SERVICE = "LocationService";
    public static final String CAB_SERVICE_CLASS = "CabServiceImpl";
    public static final String CUSTOMER_CONTROLLER = "CustomerController";
    public static final String AUTHENTICATION_CONTROLLER = "AuthenticationController";
    public static final String UTIL_CLASS = "HelloCabsUtil";

    public static final Integer MAXIMUM_WAITING_TIME = 5;
    public static final Integer MINIMUM_BOOKING_HOUR = 3;

    //REGEX
    public static final String PEAK_HOUR_REGEX = "0?[8-9]|1[089]|2[0-1]";
    public static final String RIDE_STATUS_REGEX = "Booked|Accepted|Picked|Dropped|Cancelled";
    public static final String LOCATION_NAME_REGEX = "([a-zA-Z0-9.\\s]{3,})";
    public static final String NAME = "([a-zA-Z.\\s]{1,})";
    public static final String CAB_TYPE_REGEX = "([a-zA-Z\\s]{3,})";
    public static final String CAB_NUMBER = ("([A-Z]{2}[ -][0-9]{1,2}[ -][A-Z]{1,2}[ -][0-9]{4})");
    public static final String VALID_GENDER = ("male|female|m|f|others");
    public static final String PASSWORD = ("([a-zA-Z0-9!@#$%^&*]{8,16})");
    public static final String MOBILE_NUMBER = "([6-9][0-9]{9})";

    //SEARCH NOT FOUND
    public static final String LOCATION_NOT_FOUND = "Location not found for given id";
    public static final String CAB_CATEGORY_NOT_FOUND = "Cab category not found for given id";
    public static final String CAB_NOT_FOUND = "Cab not found for given id";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found for given id";
    public static final String RIDE_NOT_FOUND = "Ride not found for given id";

    //SEARCH FOUND
    public static final String LOCATION_FOUND = "Location found for given id";
    public static final String CAB_CATEGORY_FOUND = "Cab category found for given id";
    public static final String CAB_FOUND = "Cab found for given id";
    public static final String CUSTOMER_FOUND = "Customer found for given id";
    public static final String RIDE_FOUND = "Ride found ";
    public static final String ALL_CAB_CATEGORIES_FOUND = "Found all cab categories";
    public static final String ALL_LOCATIONS_FOUND = "Found all locations";

    //DELETED
    public static final String LOCATION_DELETED = "Location deleted for given id ";
    public static final String CAB_CATEGORY_DELETED = "Cab category deleted for given id ";
    public static final String CAB_DELETED = "Cab deleted for given id ";
    public static final String CUSTOMER_DELETED = "Customer deleted for given id ";
    public static final String RIDE_CANCELLED = "Ride cancelled successfully";

    //NOT DELETED
    public static final String CUSTOMER_NOT_DELETED = "Customer is not deleted for given id : ";

    //CREATED
    public static final String LOCATION_CREATED = "Location created Successfully ";
    public static final String CAB_CATEGORY_CREATED = "Cab category created Successfully ";
    public static final String CAB_CREATED = "Cab created Successfully ";
    public static final String RIDE_CREATED = "Ride created with Successfully ";
    public static final String CUSTOMER_REGISTERED = "Customer registered successfully ";

    //UPDATED
    public static final String LOCATION_UPDATED = "Location updated ";
    public static final String CAB_CATEGORY_UPDATED = "Cab category updated";
    public static final String CAB_UPDATED = "Cab updated";
    public static final String CUSTOMER_UPDATED = "Customer updated";
    public static final String RIDE_UPDATED = "Ride updated";

    //NOT UPDATED
    public static final String LOCATION_NOT_UPDATED = "Couldn't update Location";
    public static final String CAB_NOT_UPDATED = "Couldn't update Cab";
    public static final String CUSTOMER_NOT_UPDATED = "Couldn't update Customer";
    public static final String RIDE_NOT_UPDATED = "Couldn't update Ride";

    //STATUS
    public static final String WAITING_STATUS = " Waiting for driver to accept ";
    public static final String CANCELLED_DUE_TO_UNAVAILABILITY = "Ride cancelled due to cabs unavailability, Please book again! ";
    public static final String SEARCHING_CABS = "Hold on! Searching for cabs nearby";

    //LOGIN STATUS
    public static final String LOGIN_SUCCESSFUL = "Successfully logged in";
    public static final String INVALID_USERNAME_OR_PASSWORD = "User name or password is incorrect";

    //VALIDATION
    public static final String CAB_TYPE_NOT_BLANK = "Cab type must not be Empty or null";
    public static final String DRIVER_NAME_NOT_BLANK = "Driver Name Not to be Empty :: Please Enter The Name";
    public static final String CUSTOMER_NAME_NOT_BLANK = "Customer Name Not to be Empty :: Please Enter The Name";
    public static final String PASSWORD_NOT_BLANK = "Password Should Not be Empty :: Please Set Password For Your Security";
    public static final String CAB_NUMBER_NOT_BLANK = "Cab Number Not to be Empty :: Please Enter The Cab Number";
    public static final String GENDER_NOT_BLANK = "Gender Not to be Empty :: Please Enter The Gender";
    public static final String ENTER_VALID_EMAIL = "Enter the valid Mail";
    public static final String LICENSE_NOT_BLANK = "License Number Not to be Empty :: Please Enter The License Number";
    public static final String CAR_MODEL_NOT_BLANK = "CarModel Not to be Empty :: Please Enter The CarModel";
    public static final String CAR_MODEL_SIZE = "CarModel Should be in 5 to 20 characters";
    public static final String CURRENT_LOCATION_SIZE = "Current Location Should be in between 10 to 15 Characters";
    public static final String LOCATION_NOT_BLANK = "Location name should not be Empty or null";
    public static final String RIDE_STATUS_NOT_BLANK = "Ride status should not be empty or null";
    public static final String MOBILE_NUMBER_NOT_BLANK = "Mobile Number should be exact 10 characters";
    public static final String CUSTOMER_ID_NOT_BLANK = "Customer Id should not be null or empty";
    public static final String RIDE_STATUS = "Ride status should have any one these values " + RIDE_STATUS_REGEX;
    public static final String CUSTOMER_NOT_DROPPED = " THE CUSTOMER NOT TO BE DROPPED YET :: Please check the ride status";

    //RIDE STATUS
    public static final String CAB_ON_RIDE = "On Ride";
    public static final String CAB_AVAILABLE = "Available";
    public static final String CAB_UNAVAILABLE = "UnAvailable";
    public static final String RIDE_BOOKED = "Booked";
    public static final String RIDE_COMPLETED = "Dropped";
    public static final String RIDE_PICKED = "Picked";
    public static final String RIDE_ACCEPTED = "Accepted";
    public static final String RIDE_IGNORED = "Cancelled";

    //NOT CREATED
    public static final String CAB_NOT_CREATED = "Cab Not Created for given Details ";
    public static final String STATUS_UPDATED = "Status updated ";
    public static final String CAB_TYPE_ALREADY_EXISTS = "Cab type already exists";
    public static final String LOCATION_ALREADY_EXISTS = "Location already exists";
    public static final String FIELD_NOT_BLANK = "Minimum three characters required";
    public static final String RIDE_NOT_CANCELLED = "Couldn't cancel ride after dropped";
    public static final String STATUS_NOT_FOUND = "No such ride status exists";
    public static final String CUSTOMER_NOT_PICKED = "Customer not to be picked yet";
    public static final String RIDE_NOT_ACCEPTED = "Ride not accepted yet";
    public static final String ALREADY_ON_BOARD = "You are already on board";
    public static final String INVALID_LOGIN_CREDENTIALS = "Invalid Login Credentials";
    public static final String CAB_ASSIGNED = "Hoorah! Cab assigned";
    public static final String RIDE_PICKED_ALREADY = "This ride has been picked already";
    public static final String CUSTOMER_ALREADY_EXIST = "Email or Mobile number already registered";
    public static final String CAB_CATEGORY_NOT_CREATED = "Cab category not created";

    //VALIDATION
    public static final String DECIMAL_MIN = "0.0";
    public static final int NAME_SIZE_MIN = 3;
    public static final String ENTER_VALID_CAB_TYPE ="Enter valid cab type Ex: Mini";
    public static final String FEEDBACK_ADDED = "Thanks for giving valuable feedback";
    public static final String RIDE_ACCEPTED_ALREADY = "This ride already has been accepted by another driver";
    public static final String RIDE_CANCELLED_ALREADY = "Ride Already Cancelled";
    public static final String PASSWORD_CRITERIA = "Password should contain atleast one Uppercase, atleast one Number, "
            + "atleast one Special character, minimum 8 and maximum 16 characters";
    public static final String LOCATION_NAME_SIZE = "Location name should have at least 3 characters";
    public static final String INVALID_LOCATION_NAME = "Location name should not contain any special characters or numbers";
    public static final String INVALID_FARE = "Fare should not be less than zero";
    public static final String VALID_VEHICLE_NUMBER = "Vehicle number should be valid (ex : TN 33 RD 4532)";
    public static final String VALID_PHONE_NUMBER = "Phone number should be exact 10 characters.";
    public static final String INVALID_GENDER = "Please enter valid Gender " + VALID_GENDER;
    public static final String PASSWORD_REQUIRED = "Enter your security password";
    public static final String INVALID_MOBILE_NUMBER = "Invalid mobile number, Mobile number starts with 6-9 and followed by 9 digits";
    public static final String VALID_CONSTRAINT = "check you have enter a valid id";
    public static final String MESSAGE = "message";
    public static final String SOMETHING_WENT_WRONG = "Sorry, Something went wrong";
    public static final String TIME_STAMP = "timestamp";
    public static final Double BASE_RATING = 0.0;
    public static final String USER_NOT_AUTHENTICATED = "Please login";
}