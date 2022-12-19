/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.repository;

import com.hellocabs.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h> CabRepository </h>
 * <p>
 *   Interface CabRepository which extends JpaRepository used to
 *   access the abstract method in it to cabRepository to connect the database
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 *
 */
@Repository
public interface CabRepository  extends JpaRepository<Cab, Integer> {

    /**
     * <p>
     *   An abstract method used to find cab object by
     *   cab driver's mobile number
     * </p>
     *
     * @param mobileNumber {@link Long}  mobile number to be searched
     * @return {@link Cab} returns cab object which contains the given mobile number
     *
     */
    Cab findCabByMobileNumber(Long mobileNumber);

    /**
     * <p>
     *   An abstract method used to check if the given mobile number
     *   and email exists or not, if exists returns true otherwise false.
     *   This method used to avoid if an user who entered details
     *   that are already exists
     * </p>
     *
     * @param mobileNumber {@link Long} mobile number to be searched
     * @param email {@link String} email of the object
     * @return {@link Boolean} returns true if mobile number and email exists
     *      else returns false
     *
     */
    boolean existsByMobileNumberOrEmail(Long mobileNumber, String email);

    /**
     * <p>
     *   An abstract method used to find the cab object by id
     *   and returns the object only if it is active
     * </p>
     *
     * @param id {@link Integer} mobile number to be searched
     * @param flag {@link Boolean} true or false
     * @return {@link Cab} returns cab object only it is active
     *
     */
    Cab findByIdAndIsActive(Integer id, boolean flag);

    /**
     * <p>
     *   An abstract method used to find all the cab object
     *   and returns the list of object only if it is active
     * </p>
     *
     * @param flag {@link Boolean} true or false
     * @return {@link List<Cab>} returns list of cab that are available
     */
    List<Cab> findAllByIsActive(boolean flag);

    /**
     * <p>
     *   An abstract method used to check if the given id with active
     *   exists or not, if exists returns true otherwise false.
     *   This method used to avoid if an user who entered id
     *   that are already exists or not
     * </p>
     *
     * @param id {@link Integer} to check respective id exist or not
     * @param flag {@link Boolean} to check if it is active or not
     * @return {@link Boolean} returns true if id is Active exists
     *      else returns false
     *
     */
    boolean existsByIdAndIsActive(Integer id, boolean flag);
}
