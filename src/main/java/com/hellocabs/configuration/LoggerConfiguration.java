/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.configuration;

import org.apache.log4j.Logger;

/**
 * <p>
 *   Class provide logger configuration to all classes in which
 *   logging is used in order to log data, so that in future,
 *   if there is a need to change logging configuration,
 *   can simply modify this configuration file as per requirement
 *   instead of modifying all classes which uses logger
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public class LoggerConfiguration {

    /**
     * <p>
     *   Used to get the logger object
     * </p>
     *
     * @param name {@link String} name of the class in which
     *                           logger has to be implemented
     * @return {@link Logger} created logger
     *
     */
    public static Logger getInstance(String name ) {
        return Logger.getLogger(name);
    }
}
