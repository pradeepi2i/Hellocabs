/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <h> Cab </h>
 * <p>
 *   Class is used to define the cab details and their types by using
 *   Entity object with possible information and connect to database
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 *
 */
@Entity
@Getter
@Setter
@Table(name = "cab")
public class Cab extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "cab_number")
    private String cabNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number",unique = true)
    private Long mobileNumber;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "license_number",unique = true)
    private String licenseNumber;

    @Column(name = "driver_rating")
    private Double driverRating;

    @Column(name = "cab_status")
    private String cabStatus;

    @OneToMany(mappedBy = "cab")
    private Set<Ride> rides;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "current_location")
    private String currentLocation;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "cab_category_id")
    private CabCategory cabCategory ;
}
