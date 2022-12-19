/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.repository;

import com.hellocabs.model.CabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h> CabCategoryRepository</h>
 *
 * <p>
 *   Interface CabCategoryRepository which extends JpaRepository used to
 *   access the abstract method in it to cabCategoryRepository to connect the database
 * </p>
 *
 * @version 1.0
 * @author Divya
 */
@Repository
public interface CabCategoryRepository extends JpaRepository<CabCategory,Integer> {

    /**
     * <p>
     *   This method is used to get CabCategory object by using Id and
     *   isDeleted value, to get Active Cab Category object
     * </p>
     *
     * @param id {@link Integer}to get the respective cabCategory object for given id
     * @param flag {@link Boolean} to check whether it is Active or not
     * @return {@link CabCategory} object whether it is Active with respective id
     *      or null
     *
     */
    CabCategory findByIdAndIsDeleted(Integer id, boolean flag);

    /**
     * <p>
     *   This method is used to get All CabCategory object by using
     *   isDeleted value, to get Active Cab Category objects
     * </p>
     *
     * @param flag {@link Boolean} to check whether it is Active or not
     * @return {@link List<CabCategory>} objects whether it is Active or null
     *
     */
    List<CabCategory> findAllByIsDeleted(boolean flag);

    /**
     * <p>
     *   This method is used to check CabCategory object by using Id and
     *   isDeleted value, if it is Active or not
     * </p>
     *
     * @param id {@link Integer}to check the respective cabCategory object for given id
     * @param flag {@link Boolean} to check whether it is Active or not
     * @return {@link Boolean} true whether it is Active or false
     *
     */
    boolean existsByIdAndIsDeleted(Integer id, boolean flag);

    /**
     * <p>
     *   This method is used to check CabType  by using CabType value,
     *   if it is exist or not
     * </p>
     *
     * @param cabType {@link String} to check whether the type is already exist or not
     * @return {@link Boolean} true if given cabType exist or false
     *
     */
    boolean existsByCabType(String cabType);
}
