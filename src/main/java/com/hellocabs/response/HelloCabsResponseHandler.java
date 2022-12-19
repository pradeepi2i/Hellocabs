/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *   Response handler which gets the response object
 *   and add some other info to the response in cab
 *   booking application
 * </p>
 *
 * @author : Pradeep
 * created on 01/11/2022
 * @version 1.0
 *
 */
public class HelloCabsResponseHandler {

    /**
     * <p>
     *   This method used to modify the existing response by adding
     *   the status code and additional message along with the object
     * </p>
     *
     * @param message {@link String} response message
     * @param httpStatus {@link HttpStatus} status for the request
     * @param object {@link Object} returned object by processing the request
     * @return {@link ResponseEntity<Object>} response object
     *
     */
    public static ResponseEntity<?> generateResponse(String message, HttpStatus httpStatus,
                                                     Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        map.put("message", message);
        map.put("status", httpStatus.value());
        map.put("data", object);
        return new ResponseEntity<>(map, httpStatus);
    }

    /**
     * <p>
     *   This method used to modify the existing response by adding
     *   the status code and additional message for the response
     * </p>
     *
     * @param message {@link String} response message
     * @param httpStatus {@link HttpStatus} status for the request
     * @return {@link ResponseEntity<Object>} response object
     *
     */
    public static ResponseEntity<?> generateResponse(String message, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        map.put("message", message);
        map.put("status", httpStatus.value());
        return new ResponseEntity<>(map,httpStatus);
    }

    /**
     * <p>
     *   This method used to modify the existing response by adding
     *   the status code and additional message for the response
     * </p>
     *
     * @param message {@link String} response message
     * @param httpStatus {@link HttpStatus} status for the request
     * @param collections {@link Collection<Object>} returned a collection
     *      object by processing the request
     * @return {@link ResponseEntity<Object>} response object
     *
     */
    public static ResponseEntity<?> generateResponse(String message, HttpStatus httpStatus,
            Collection<Object> collections) {
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        map.put("message", message);
        map.put("status", httpStatus.value());
        map.put("data", collections);
        return new ResponseEntity<>(map,httpStatus);
    }
}
