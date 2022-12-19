/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.response.HelloCabsResponseHandler;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.service.CabCategoryService;

/**
 * <p>
 * CabCategory controller class has the methods that handles
 * CRUD operations of location.
 * </p>
 *
 * @author  Divya
 * @version 1.0 Oct-26-2022
 *
 */
@RestController
@RequestMapping("cabcategories")
@RequiredArgsConstructor
public class CabCategoryController {

    private final CabCategoryService cabCategoryService;

    /**
     * <p>
     *   This method is to add cab category Details if the
     *   location is not already exist
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the
     *                            cab category to be added is given
     * @return {@link ResponseEntity<CabCategoryDto>} inserted cab category id is
     *                            returned as message with http status
     *
     */
    @PostMapping
    private ResponseEntity<?> addCabCategory(@Valid @RequestBody
            CabCategoryDto cabCategoryDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CAB_CATEGORY_CREATED,
                HttpStatus.OK, cabCategoryService.createCabCategory(cabCategoryDto));
    }

    /**
     * <p>
     *   This method is get the cab category Details if the given id exits
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to
     *        be searched is given
     * @return {@link ResponseEntity<CabCategoryDto>} searched cab category is returned
     *
     */
    @GetMapping("{id}")
    private ResponseEntity<?> searchCabCategoryById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CAB_CATEGORY_FOUND,
                HttpStatus.FOUND, cabCategoryService.getCabCategoryById(id));
    }

    /**
     * <p>
     *   This method is to update specific cab category Details if the given id exits
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category field
     *                                            to be updated is given
     * @param id {@link Integer} id to be searched and updated
     * @return {@link ResponseEntity<CabCategoryDto>} updated cab category is returned
     */
    @PatchMapping("{id}")
    private ResponseEntity<?> updateCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto,
                                             @PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CAB_CATEGORY_UPDATED,
                HttpStatus.OK, cabCategoryService.updateCabCategoryById(cabCategoryDto, id));
    }

    /**
     * <p>
     *   This method is to update cab category Details if the given id exits
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category field
     *                                            to be updated is given
     * @return {@link ResponseEntity<CabCategoryDto>} updated cab category is returned
     */
    @PutMapping
    private ResponseEntity<?> updateCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CAB_CATEGORY_UPDATED,
                HttpStatus.OK, cabCategoryService.updateCabCategory(cabCategoryDto));
    }

    /**
     * <p>
     *   This method is to delete cab category Details only if the given id exits.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to
     *        be deleted is given
     * @return {@link ResponseEntity<String>} gets the message whether the
     *         cab category is deleted or not
     */
    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteCabCategoryById(@PathVariable Integer id) {
            return HelloCabsResponseHandler.generateResponse(cabCategoryService
                    .deleteCabCategoryById(id), HttpStatus.OK);
    }

    /**
     * <p>
     *   This method is to display all cab category Details.
     * </p>
     *
     * @return {@link ResponseEntity<List>} retrieves all the cab categories
     */
    @GetMapping()
    private ResponseEntity<?> getAllCabCategories() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_FOUND, HttpStatus.OK, cabCategoryService.retrieveAllCabCategories());
    }
}
