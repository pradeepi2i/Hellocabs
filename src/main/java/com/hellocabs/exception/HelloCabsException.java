/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.exception;

import lombok.NoArgsConstructor;

/**
 * <p>
 *   HelloCabsException is the class of those exceptions that can be thrown
 *   during the normal operation of the hello cabs application.
 * </p>
 *
 * @author : Divya
 * created on 25/10/2022
 * @version 1.0
 *
 */
@NoArgsConstructor
public class HelloCabsException extends RuntimeException {

    /**
     * <p>
     *   Constructs a new HelloCabs exception with the specified detail message.
     * </p>
     *
     * @param message {@link String} the detail message (which is saved for
     *                             later retrieval by the getMessage() method).
     *
     */
    public HelloCabsException(String message) {
        super(message);
    }

    /**
     * <p>
     *   Constructs a new HelloCabs exception with the specified detail message and cause.
     * </p>
     *
     * @param message {@link String} the detail message (which is saved for
     *                             later retrieval by the getMessage() method).
     * @param cause {@link Throwable} the cause (which is saved for later
     *                               retrieval by the getCause() method).
     *
     */
    public HelloCabsException(String message, Throwable cause) {
        super(message,cause);
    }

    /**
     * <p>
     *   Constructs a new HelloCabs exception with the throwable cause.
     * </p>
     *
     * @param cause {@link Throwable} the cause (which is saved for
     *                               later retrieval by the getCause() method).
     *
     */
    public HelloCabsException(Throwable cause) {
        super(cause);
    }
}
