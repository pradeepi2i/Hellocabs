/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CabCategoryMapper;
import com.hellocabs.model.CabCategory;
import com.hellocabs.repository.CabCategoryRepository;
import com.hellocabs.service.CabCategoryService;
import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *   CabCategoryService class consists of the functions that handles
 *   the CRUD for the cab category which is used during the normal
 *   operation of the hello cabs application.
 * </p>
 *
 * @author : Divya
 * created on 21/10/2022
 * @version 1.0
 *
 */
@Service
@RequiredArgsConstructor
public class CabCategoryServiceImpl implements CabCategoryService {

    private final Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.CAB_CATEGORY_SERVICE);
    private final CabCategoryRepository cabCategoryRepository;

    /**
     * <p>
     *   This method is to add cab category Details by getting and converting the cab category
     *   dto into cab category entity and  saves into the cab category repository and returns
     *   message with created cab category id only if the cab category name is not already
     *   exits otherwise it throws exception.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the
     *                           cab category to be added is given
     * @return {@link CabCategoryDto} inserted cab category id is returned
     *
     */
    public CabCategoryDto createCabCategory(CabCategoryDto cabCategoryDto) {
        CabCategory cabCategory = CabCategoryMapper
                .cabCategoryDtoToCabCategory(cabCategoryDto);

        /* checks whether the cab type not exists already */
        if (!cabCategoryRepository.existsByCabType(cabCategory.getCabType())) {
            CabCategoryDto cabCategoryDto1 = CabCategoryMapper.cabCategoryToCabCategoryDto
                    (cabCategoryRepository.save(cabCategory));
            logger.info(HelloCabsConstants.CAB_CATEGORY_CREATED + cabCategoryDto1.getId());
            return cabCategoryDto1;
        }
        logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_CREATED);
        throw new HelloCabsException(HelloCabsConstants.CAB_TYPE_ALREADY_EXISTS);
    }

    /**
     * <p>
     *   This method is to get cab category Details by getting cab category id
     *   and retrieves the cab category from the cab category repository and
     *   returns searched cab category as dto only if the given id is
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to
     *                           be searched is given
     * @return {@link CabCategoryDto} searched cab category is returned if present
     *         otherwise null
     *
     */
    public CabCategoryDto getCabCategoryById(Integer id) {
        CabCategory cabCategory = cabCategoryRepository.findByIdAndIsDeleted(id, false);

        if (null != cabCategory) {
            logger.info(HelloCabsConstants.CAB_CATEGORY_FOUND + id);
            return CabCategoryMapper.cabCategoryToCabCategoryDto(cabCategory);
        }
        logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
    }

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
    public CabCategoryDto updateCabCategoryById(CabCategoryDto cabCategoryDto, Integer id) {

        /* checks whether the cab id exits already */
        if (cabCategoryRepository.existsByIdAndIsDeleted(cabCategoryDto.getId(), false)) {
            logger.info(HelloCabsConstants.CAB_CATEGORY_UPDATED + cabCategoryDto.getId());
            return CabCategoryMapper.cabCategoryToCabCategoryDto(cabCategoryRepository
                    .save(CabCategoryMapper.cabCategoryDtoToCabCategory(cabCategoryDto)));
        }
        logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
    }

    /**
     * <p>
     * This method is to update cab category Details by getting and converting the cab category
     * dto into cab category entity and saves into the cab category repository and returns
     * updated cab category as dto only if the given id is already exits otherwise
     * it throws exception.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category to be updated is given
     * @return {@link CabCategoryDto} updated cab category is returned
     */
    public CabCategoryDto updateCabCategory(CabCategoryDto cabCategoryDto) {

        /* checks whether the cab id exits already */
        if (cabCategoryRepository.existsByIdAndIsDeleted(cabCategoryDto.getId(), false)) {
            logger.info(HelloCabsConstants.CAB_CATEGORY_UPDATED + cabCategoryDto.getId());
            return CabCategoryMapper.cabCategoryToCabCategoryDto(cabCategoryRepository
                    .save(CabCategoryMapper.cabCategoryDtoToCabCategory(cabCategoryDto)));
        }
        logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
    }

    /**
     * <p>
     *   This method is to delete cab category Details by getting the id to be deleted
     *   and deletes the cab category in cab category repository and returns the message
     *   only if the given id is already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to
     *        be deleted is given
     * @return {@link String} returns deleted message if the cab category of
     *         given id is deleted otherwise throws exception
     *
     */
    public String deleteCabCategoryById(Integer id) {
        CabCategory cabCategory = cabCategoryRepository
                .findByIdAndIsDeleted(id, false);

        if (null == cabCategory) {
            logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        }
        cabCategory.setDeleted(true);
        cabCategoryRepository.save(cabCategory);
        logger.info(HelloCabsConstants.CAB_CATEGORY_DELETED + cabCategory.getId());
        return HelloCabsConstants.CAB_CATEGORY_DELETED;
    }

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
    public List<CabCategoryDto> retrieveAllCabCategories() {
        logger.info(HelloCabsConstants.ALL_CAB_CATEGORIES_FOUND);
        return CabCategoryMapper.cabCategoriesToCabCategoryDtos
                (cabCategoryRepository.findAllByIsDeleted(false));
    }
}
