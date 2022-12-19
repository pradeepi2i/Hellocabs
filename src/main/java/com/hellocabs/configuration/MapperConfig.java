/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.configuration;

import org.modelmapper.ModelMapper;

/**
 * <p>
 *   Class provide Mapper configuration to all classes to access
 *   the model mapper object
 * </p>
 *
 * @author : Jaganathan R
 * @version 1.0
 *
 */
public class MapperConfig {

    /**
     * <p>
     *   Used to get the logger object to access for
     *   all files within HelloCabApplication
     * </p>
     *
     * @return object mapper {@link ModelMapper}
     *
     */
    public static ModelMapper getConfigure() {
        return new ModelMapper();
    }
}
