/*
 *  Copyright (c) All rights reserved Ideas2IT
 */
package com.hellocabs.service;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.model.Customer;

import java.util.List;

/**
 *  <h> customerService </h>
 * <p>
 *   CustomerService interface has abstract methods of customers.
 * </p>
 *
 *  @author gautam.
 *  @version 1.0.
 */
public interface CustomerService {

    /**
     * <p>
     *   This method is to add customer Details by getting and converting the customer
     *   dto into customer entity and  saves into the customer repository and returns
     *   message with created customer id only if the customer mobile number and email
     *   id is not already exits otherwise it throws exception.
     * </p>
     *
     * @param customerDto {@link CustomerDto} for which the customer to be added is given
     * @return {@link CustomerDto} inserted customer id is returned with message
     *
     */
    CustomerDto createCustomer(CustomerDto customerDto);

    /**
     * <p>
     *   This method is to get customer Details by getting customer id
     *   and retrieves the customer from the customer repository and
     *   returns searched customer as dto only if the given id is
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param customerId {@link Integer} for which the id of the customer need to be searched is given
     * @return {@link CustomerDto} searched customer is returned
     *
     */
    CustomerDto viewCustomerById(Integer customerId);

    /**
     * <p>
     *   This method is to update customer Details by getting and converting the customer
     *   dto into customer entity and saves into the customer repository and returns
     *   updated customer as dto only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param customerDto {@link CustomerDto} for which the customer to be updated is given
     * @return {@link CustomerDto} updated customer is returned
     *
     */
    CustomerDto updateCustomer(CustomerDto customerDto);

    /**
     * <p>
     *   This method is to delete customer Details by getting the id to be deleted
     *   and deletes the customer in customer repository and returns the message
     *   only if the given id is already exits otherwise it throws exception.
     * </p>
     *
     * @param customerId {@link Integer} for which the id of the customer need to be deleted is given
     * @return {@link String} returns the message if the customer is deleted
     *
     */
    String deleteCustomerById(Integer customerId);

    /**
     * <p>
     *   This method is to retrieve customer Details and converts the customer
     *   entity into customer dto and returns the customers from the customer
     *   repository
     * </p>
     *
     * @return {@link List<CustomerDto>} retrieves all the customers
     *
     */
    List<CustomerDto> retrieveCustomers();

    /**
     * <p>
     *   This method is to get customer Details by getting customer mobile number
     *   and retrieves the customer from the customer repository and returns
     *   searched customer as dto only if the given mobile number already
     *   exits otherwise it throws exception.
     * </p>
     *
     * @param mobileNumber {@link Integer} for which the mobile number of the
     *                                   customer need to be searched is given
     * @return {@link Customer} searched customer is returned
     *
     */
    Customer findByCustomerMobileNumber(long mobileNumber);
}