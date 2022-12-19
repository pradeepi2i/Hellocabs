/*
 *  Copyright (c) All rights reserved Ideas2IT
 */

package com.hellocabs.controller;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.CustomerService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
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

/**
 * <h> customerController </h>
 * <p>
 *   This class is used to get and access customer details and
 *   implements customer CRUD operations.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = LoggerConfiguration.getInstance(HelloCabsConstants.CUSTOMER_CONTROLLER);

    /**
     * <p>
     *   This is Post call method used to create customer details and passes to customerService.
     * </p>
     *
     * @param customerDto {@link CustomerDto} contains customer details.
     * @return {@link ResponseEntity<CustomerDto>} returns created customerId and status.
     *
     */
    @PostMapping
    private ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_REGISTERED,
                            HttpStatus.CREATED, customerService.createCustomer(customerDto));
    }

    /**
     * <p>
     *   This is Get call method used to search customer from database by using its id
     *   if exist it gets the object related to this id if not gets null.
     * </p>
     *
     * @param customerId {@link Integer}
     * @return {@link ResponseEntity<CustomerDto>}returns searched customer details.
     *
     */
    @GetMapping("{customerId}")
    private ResponseEntity<?> viewCustomerById(@PathVariable Integer customerId) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_FOUND,
                    HttpStatus.OK, customerService.viewCustomerById(customerId));
    }

    /**
     * <p>
     *    This is Post call method used to update customer and passes to customerService .
     * </p>
     *
     * @param customerDto {@link  CustomerDto}
     * @return {@link ResponseEntity<CustomerDto>}returns updated customerId.
     *
     */
    @PutMapping
    private ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_UPDATED,
                HttpStatus.OK, customerService.updateCustomer(customerDto));
    }

    /**
     * <p>
     *    This is Post call method used to update customer and passes to customerService .
     * </p>
     *
     * @param customerDto {@link  CustomerDto}
     * @param id {@link Integer}
     * @return {@link ResponseEntity<CustomerDto>}returns updated customerId.
     *
     */
    @PatchMapping("{id}")
    private ResponseEntity<?> updateCustomerById(@Valid @RequestBody CustomerDto customerDto,
            @PathVariable Integer id) {
            return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_UPDATED,
                    HttpStatus.OK, customerService.updateCustomer(customerDto));
    }

    /**
     * <p>
     *    This is Delete call method used to delete customer from the database by using its id.
     * </p>
     *
     * @param customerId {@link Integer}
     * @return {@link ResponseEntity<String>}returns deleted customerId.
     *
     */
    @DeleteMapping("{customerId}")
    private ResponseEntity<?> deleteCustomerById(@PathVariable Integer customerId) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_DELETED,
                HttpStatus.OK);

    }

    /**
     * <p>
     *    This Method is used to display all customers.
     * </p>
     *
     * @return {@link ResponseEntity< List >} returns all customers.
     *
     */
    @GetMapping
    private ResponseEntity<?> retrieveAllCustomers() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_FOUND,
                        HttpStatus.OK,customerService.retrieveCustomers());

    }
}
