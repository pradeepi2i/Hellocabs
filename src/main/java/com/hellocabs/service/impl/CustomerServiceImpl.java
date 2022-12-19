/*
 *  Copyright (c) All rights reserved Ideas2IT
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CustomerMapper;
import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import com.hellocabs.service.CustomerService;
import com.hellocabs.validation.HelloCabsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h> customerServiceImpl </h>
 * <p>
 *   This class defines implementation of customerService interface methods.
 * </p>
 *
 *  @author gautam.
 *  @version 1.0.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

     private final CustomerRepository customerRepository;

     private final PasswordEncoder passwordEncoder;

    /**
     * <h> customerServiceImpl </h>
     * <p>
     *   This method is used to create customer by customerDto
     *   and convert customerDto to customer and
     *   pass to repository.
     * </p>
     *
     * @param customerDto {@link CustomerDto} contains customer details.
     * @return  {@link CustomerDto}returns createdCustomer.
     *
     */
    public CustomerDto createCustomer(CustomerDto customerDto) {

        if (HelloCabsValidation.isValidMobileNumber(customerDto.getCustomerMobileNumber())
                && !customerRepository.existsByCustomerMobileNumberOrCustomerEmail(
                customerDto.getCustomerMobileNumber(), customerDto.getCustomerEmail())) {
            customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            return CustomerMapper.convertCustomerToCustomerDto(customerRepository.save(CustomerMapper.convertCustomerDtoToCustomer(customerDto)));
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_ALREADY_EXIST);
    }

    /**
     * <p>
     *   This method is used to search customer by its id and
     *   converts customer to customerDto.
     * </p>
     *
     * @param customerId {@link Integer} id to be searched.
     * @return {@link CustomerDto} returns customerDto object.
     */
    @Override
    public CustomerDto viewCustomerById(Integer customerId) {
        Customer customer = customerRepository.findByCustomerIdAndIsDeleted(customerId, false);

        if(null != customer) {
            return CustomerMapper.convertCustomerToCustomerDto(customer);
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_FOUND) ;
    }

    /**
     * <p>
     *   This method is used to update existing customers and
     *   convert customerDto to customer and add into repository.
     * </p>
     *
     * @param  customerDto {@link CustomerDto} contains updatedCustomer details.
     * @return {@link CustomerDto}returns updatedCustomer.
     *
     */
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {

        if (HelloCabsValidation.isValidMobileNumber(customerDto.getCustomerMobileNumber())
                && customerRepository.existsById(customerDto.getCustomerId())) {
            Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
            return CustomerMapper.convertCustomerToCustomerDto(customerRepository.save(customer));
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
    }

    /**
     * <p>
     *    This Method is used to find customer by its id
     *    and delete if is exist.
     * </p>
     *
     * @param customerId {@link Integer} id to be deleted.
     * @return {@link String}returns true or false.
     *
     */
    @Override
    public String deleteCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if(null != customer) {
            customer.setIsDeleted(true);
            customerRepository.save(customer);
            return HelloCabsConstants.CUSTOMER_DELETED;
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DELETED);
    }

    /**
     * <p>
     *    This Method is used to display all customers and convert
     *    customer into customerDto.
     * </p>
     *
     * @return {@link List<CustomerDto> }returns all customers.
     *
     */
    @Override
    public List<CustomerDto> retrieveCustomers() {
        return CustomerMapper.convertCustomersToCustomerDtos(customerRepository
                .findAllByIsDeleted(false));
    }

    /**
     * <p>
     *   Implement this method used to find customer object by
     *   customer's mobile number
     * </p>
     *
     * @param mobileNumber {@link Long}  mobile number to be searched
     * @return {@link Customer} returns cab object which contains the given mobile number
     *
     */
    public Customer findByCustomerMobileNumber(long mobileNumber) {
        return customerRepository.findByCustomerMobileNumber(mobileNumber);
    }
}
