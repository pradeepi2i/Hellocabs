/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hellocabs.model.Customer;

import java.util.List;

/**
 * <p>
 *   Interface that provides abstract methods
 *   to perform CRUD operations for Customer object
 *   to store data in database
 * </p>
 *
 * @author : Gautam
 * @version 1.0
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    /**
     * <p>
     *   This method is used to find a Customer object
     *   by using Mobile Number
     * </p>
     *
     * @param mobileNumber {@link Long} to check if exist mobileNumber or not
     * @return {@link Customer}if it exist mobileNumber it returns respective
     *       customer object or null
     *
     */
    Customer findByCustomerMobileNumber(Long mobileNumber);

    /**
     * <p>
     *   This method is used to check  customer object if exist for given
     *   mobileNumber and email
     * </p>
     *
     * @param mobileNumber {@link Long} to check whether object exist for given mobileNumber
     * @param email {@link String} to check whether object exist for given email
     * @return {@link Boolean}if it exist mobileNumber and email it returns
     *       true or false
     *
     */
    boolean existsByCustomerMobileNumberOrCustomerEmail(Long mobileNumber, String email);

    /**
     * <p>
     *   This method is used to get customer object by using Customer Id
     *   and value of isActive
     * </p>
     *
     * @param customerId {@link Integer} used to check if exist in database
     * @param flag {@link Boolean} used to check if customer is active or not
     * @return {@link Customer}object for respective id with Active or null
     *
     */
    Customer findByCustomerIdAndIsDeleted(Integer customerId, boolean flag);

    /**
     * <p>
     *   This method is used to get all Customer object whether if it is Active by
     *   using value of isDeleted
     * </p>
     *
     * @param flag {@link Boolean} used to check whether it is active or not
     * @return {@link List<Customer>} objects one only it is Active
     *
     */
    List<Customer> findAllByIsDeleted(boolean flag);


}
