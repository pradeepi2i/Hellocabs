/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service;

import com.hellocabs.dto.CabCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CabCategoryService {

    /**
     * <p>
     *   This method is to add cab category Details by getting and converting the cab category
     *   dto into cab category entity and  saves into the cab category repository and returns
     *   message with created cab category id only if the cab category name is not already
     *   exits otherwise it throws exception.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category to be added is given
     * @return {@link CabCategoryDto} inserted cab category id is returned with message
     *
     */
    CabCategoryDto createCabCategory(CabCategoryDto cabCategoryDto);

    /**
     * <p>
     *   This method is to update specific cab category details by getting category id,
     *   find cabcategory and converting the cab category dto into cab category entity
     *   and save only the specific details into the cab category repository and returns
     *   updated cab category as dto only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category to be updated is given
     * @param id {@link Integer} id to be searched and updated
     * @return {@link CabCategoryDto} updated cab category is returned
     *
     */
    CabCategoryDto updateCabCategoryById(CabCategoryDto cabCategoryDto, Integer id);

    /**
     * <p>
     *   This method is to update cab category Details by getting and converting the cab category
     *   dto into cab category entity and saves into the cab category repository and returns
     *   updated cab category as dto only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category to be updated is given
     * @return {@link CabCategoryDto} updated cab category is returned
     *
     */
    CabCategoryDto updateCabCategory(CabCategoryDto cabCategoryDto);

    /**
     * <p>
     *   This method is to get cab category Details by getting cab category id
     *   and retrieves the cab category from the cab category repository and
     *   returns searched cab category as dto only if the given id is
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to be searched is given
     * @return {@link CabCategoryDto} searched cab category is returned
     *
     */
    CabCategoryDto getCabCategoryById(Integer id);

    /**
     * <p>
     *   This method is to delete cab category Details by getting the id to be deleted
     *   and deletes the cab category in cab category repository and returns the message
     *   only if the given id is already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to be deleted is given
     * @return {@link String} returns the message if the cab category is deleted
     */
    String deleteCabCategoryById(Integer id);

    /**
     * <p>
     *   This method is to retrieve cab category Details and converts the cab category
     *   entity into cab category dto and returns the cab categories from the cab category
     *   repository
     * </p>
     *
     * @return {@link List<CabCategoryDto>} retrieves all the cab categories
     *
     */
    List<CabCategoryDto> retrieveAllCabCategories();
}
