/*
 * All copyrights reserved.
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellocabs.constants.HelloCabsConstants;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Set;

/**
 * <p>
 *     CustomerDto have the dataMembers of customer.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@Data
@Validated
@NoArgsConstructor
public class CustomerDto {

    public CustomerDto(Integer customerId) {
        this.customerId = customerId;
    }

    private Integer customerId;

    @NotBlank(message = HelloCabsConstants.CUSTOMER_NAME_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.NAME)
    private String firstName;

    @NotBlank(message = HelloCabsConstants.CUSTOMER_NAME_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.NAME)
    private String lastName;

    @NotNull(message = HelloCabsConstants.MOBILE_NUMBER_NOT_BLANK)
    @Range(min = 10, message = HelloCabsConstants.VALID_PHONE_NUMBER)
    private Long customerMobileNumber;

    @Email(message = HelloCabsConstants.ENTER_VALID_EMAIL)
    private String customerEmail;

    @NotBlank(message = HelloCabsConstants.PASSWORD_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.PASSWORD, message = HelloCabsConstants.PASSWORD_CRITERIA)
    private String password;

    @JsonIgnore
    private boolean isDeleted;

    private Set<RideDto> rides;


}
