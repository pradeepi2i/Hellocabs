/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Customer;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *   UserDetailsServiceImpl class that implements {@link UserDetailsService}
 *   checks the user in database and returns if available using the
 *   overridden method
 * </p>
 *
 * @author : Divya
 * created on 03/11/2022
 * @version 1.0
 *
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerService customerService;
    private final CabService cabService;

    /**
     * <p>
     *   This Override method loads the user by its mobile number by checking in both cab
     *   and customer database which are accessed through their corresponding services
     *   and if the customer and cab objects are null then the exception is thrown
     * </p>
     *
     * @param mobileNumber the mobile number identifying the user whose data is required.
     * @return {@link UserDetails} returns user of given mobile number.
     * @throws UsernameNotFoundException  if the user could not be found for given mobile number
     *
     */
    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        Customer customer = customerService.findByCustomerMobileNumber(Long.parseLong(mobileNumber));
        Cab cab = cabService.findCabByMobileNumber(Long.parseLong(mobileNumber));

        if (null != customer ) {
            String userName = Long.toString(customer.getCustomerMobileNumber());
            return new User(userName, customer.getPassword(), new ArrayList<>());
        } else if (null != cab) {
            String userName = Long.toString(cab.getMobileNumber());
            return new User(userName, cab.getPassword(), new ArrayList<>());
        }
        /*throws exception when the given credentials are invalid*/
        throw new UsernameNotFoundException(HelloCabsConstants.INVALID_USERNAME_OR_PASSWORD);
    }
}
