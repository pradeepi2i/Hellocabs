/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.exceptionHandler;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.exception.HelloCabsException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h> HelloCabsAdvisor </h>
 * <p>
 *   Class which is to handle the exceptions from all over the
 *   application by using ControllerAdvice and ExceptionHandler
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 *
 */
@RestControllerAdvice
public class HelloCabsAdvisor {

    /**
     * <p>
     *   Method used to show the  exception which is handle by MethodArgumentNotValidException
     *   with the help of exception handler method
     * </p>
     *
     * @param methodArgNotValidException {@link MethodArgumentNotValidException}
     *      exception thrown due to validation
     * @return {@link String} returns the message of the exception
     *
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public Map<String,Object> exceptionHandler(MethodArgumentNotValidException methodArgNotValidException) {
        Map<String,Object> errors = new HashMap<>();
        methodArgNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(HelloCabsConstants.TIME_STAMP, LocalDateTime.now());
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    /**
     * <p>
     *   Method used to show the  exception which is handle by RuntimeException
     *   with the help of exception handler method
     * </p>
     *
     * @param helloCabsException {@link HelloCabsException} exception thrown in hello cab application
     * @return {@link String} returns the message of the exception
     *
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = HelloCabsException.class)
    public Map<String, Object> exceptionHandler(HelloCabsException helloCabsException) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put(HelloCabsConstants.TIME_STAMP, LocalDateTime.now());
        errors.put(HelloCabsConstants.MESSAGE, helloCabsException.getMessage());
        return errors;
    }

    /**
     * <p>
     *   Method used to handle the exception which is thrown by hibernate
     *   with the help of exception handler method
     * </p>
     *
     * @param hibernateException {@link HibernateException} exception thrown in hello cab application
     * @return {@link String} returns the message of the exception
     *
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(value = HibernateException.class)
    public Map<String, Object> exceptionHandler(HibernateException hibernateException) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put(HelloCabsConstants.TIME_STAMP, LocalDateTime.now());
        errors.put(HelloCabsConstants.MESSAGE, HelloCabsConstants.VALID_CONSTRAINT);
        return errors;
    }
}
