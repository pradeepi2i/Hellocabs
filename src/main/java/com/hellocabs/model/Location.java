/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 *   Location class has the getters and setters meant to hold the
 *   location object consists the fields that are related to
 *   location
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Entity
@Getter
@Setter
@Table(name = "location")
public class Location extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
