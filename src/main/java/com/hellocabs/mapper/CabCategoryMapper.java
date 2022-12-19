/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.model.CabCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *   CabCategoryMapper class consists of the functions that converts
 *   dto of the CabCategory object to entity and vice versa which is
 *   used during the normal operation of the hello cabs application.
 * </p>
 *
 * @author : Divya
 * created on 21/10/2022
 * @version 1.0
 *
 */
public class CabCategoryMapper {

    /**
     * <p>
     *   This method is used to convert cab category entity into cab category dto
     *   after the application gets input from user
     * </p>
     *
     * @param cabCategory {@link CabCategory} entity object to be converted
     * @return CabCategoryDto {@link CabCategoryDto} converted dto object
     *
     */
    public static CabCategoryDto  cabCategoryToCabCategoryDto(CabCategory cabCategory) {
        return MapperConfig.getConfigure().map(cabCategory, CabCategoryDto.class);
    }

    /**
     * <p>
     *   This method is used to convert cab category dto into cab category entity
     *   after the application sends output from database
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} dto object to be converted
     * @return CabCategory {@link CabCategory} converted entity object
     *
     */
    public static CabCategory cabCategoryDtoToCabCategory(CabCategoryDto cabCategoryDto) {
        return MapperConfig.getConfigure().map(cabCategoryDto, CabCategory.class);
    }

    /**
     * <p>
     *   This method is used to convert list of cab categories into list of cab categories
     *   dto after the application sends list of cab categories as output from database
     * </p>
     *
     * @param cabCategories {@link CabCategory} dto object to be converted
     * @return {@link CabCategoryDto} converted entity object
     *
     */
    public static List<CabCategoryDto> cabCategoriesToCabCategoryDtos(List<CabCategory> cabCategories) {
        List<CabCategoryDto> cabCategoryDtos = new ArrayList<>();

        /*Iterate list of cabcategory and convert those cabcategories into list of cabcategoryDto*/
        for (CabCategory cabCategory: cabCategories) {
            cabCategoryDtos.add(cabCategoryToCabCategoryDto(cabCategory));
        }
        return cabCategoryDtos;
    }
}
