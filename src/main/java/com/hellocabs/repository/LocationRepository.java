/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.repository;

import com.hellocabs.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *   Interface that provides abstract methods
 *   to perform CRUD operations for location entity
 *   to store data in database
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    /**
     * <p>
     *   This method used to find active cab by using id
     * </p>
     *
     * @param id {@link Integer} for which the cab category to be added is given
     * @param flag {@link Boolean} value to check whether the cab is active or not
     * @return {@link Location} location object one and only if the given id is
     *      active else returns null
     *
     */
    Location findByIdAndIsDeleted(Integer id, boolean flag);

    /**
     * <p>
     *   This method used to find list active cab
     * </p>
     *
     * @param flag {@link Boolean} value to check whether the cab is active or not
     * @return {@link List<Location>} list of active location object else empty list
     *
     */
    List<Location> findAllByIsDeleted(boolean flag);

    /**
     * <p>
     *   This method used to check whether the given
     *   location is exists or not
     * </p>
     *
     * @param locationName {@link String} location name to be checked
     * @return {@link Boolean} returns true if the given name is
     *      already exists else false
     *
     */
    boolean existsByLocationName(String locationName);

    /**
     * <p>
     *   This method used to check whether the given
     *   location is exists or not
     * </p>
     *
     * @param id {@link Integer} for which the cab category
     *                          to be added is given
     * @param flag {@link Boolean} value to check whether the cab is active or not
     * @return {@link Boolean} returns true if the given name is
     *       already exists else false
     *
     */
    boolean existsByIdAndIsDeleted(Integer id, boolean flag);
}
