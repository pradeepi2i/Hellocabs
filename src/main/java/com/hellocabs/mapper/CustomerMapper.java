/*
 * All copyrights reserved.
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  It has the method to convert customerDto to customer
 *  and customer to customerDto.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
public class CustomerMapper {

    /**
     * <p>
     *   Converting customerDto to customer.
     * </p>
     *
     * @param customerDto {@link CustomerDto} dto object to be converted
     * @return {@link Customer} converted customer object
     *
     */
    public static Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
        return MapperConfig.getConfigure().map(customerDto, Customer.class);
    }

    /**
     * <p>
     *   Converting customer to customerDto.
     * </p>
     *
     * @param customer {@link Customer} entity object to be converted
     * @return {@link CustomerDto} converted customerDto object
     *
     */
    public static CustomerDto convertCustomerToCustomerDto(Customer customer) {
        return MapperConfig.getConfigure().map(customer, CustomerDto.class);
    }

    /**
     * <p>
     *   This method is used to convert list of customers into list of customer dto
     * </p>
     *
     * @param customers {@link Customer} list of customers to be converted
     * @return {@link List<CustomerDto>} converted list of customerDto
     *
     */
    public static List<CustomerDto> convertCustomersToCustomerDtos(List<Customer> customers) {
        List<CustomerDto> customerDtos = new ArrayList<>();

        /*Iterate list of customer and convert those customer into list of customerDto*/
        for (Customer customer : customers) {
            CustomerDto customerDto = CustomerMapper.convertCustomerToCustomerDto(customer);
            customerDtos.add(customerDto);  
        }
        return customerDtos;
    }
}
