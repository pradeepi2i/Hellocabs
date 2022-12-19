/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CabDto;
import com.hellocabs.model.Cab;

/**
 * <h> CabMapper </h>
 * <p>
 *   Class is used to convert the cabDto object to cab object and
 *   cab object to cabDto object respectively and returns to respected classes
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
public class CabMapper {

    /**
     * <p>
     *   Method used to change the cab object into cabDto with the help
     *   of cabDto entity object and return to respective method
     * </p>
     *
     * @param cab {@link Cab}get valid information from user
     * @return {@link CabDto}returns the cabDto object
     */
    public static CabDto convertCabToCabDto(Cab cab) {
        return MapperConfig.getConfigure().map(cab, CabDto.class);
    }

    /**
     * <p>
     *   Method used to change the cabDto object into cab with the help
     *   of cab entity object and return to respective method
     * </p>
     *
     * @param cabDto {@link CabDto}get valid information from user
     * @return {@link Cab}returns the cab object
     */
    public static Cab convertCabDtoToCab(CabDto cabDto) {
        return MapperConfig.getConfigure().map(cabDto, Cab.class);
    }
}
