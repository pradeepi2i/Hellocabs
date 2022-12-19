/*
 * All copyrights reserved.
 */

package com.hellocabs.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * <h>Customer entity</h>
 * <p>
 *   Customer class have the dataMembers of customer.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer customerId;

    @Column(name = "first_name",length = 30)
    private String firstName;

    @Column(name = "last_name",length = 30)
    private String lastName;

    @Column(name = "mobile_number",unique = true)
    private Long customerMobileNumber;

    @Column(name = "email",length = 20,unique = true)
    private String customerEmail;

    @Column(name = "password",length = 20)
    private String password;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer")
    private Set<Ride> rides;

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
