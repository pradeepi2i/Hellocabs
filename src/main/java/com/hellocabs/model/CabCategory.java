/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.List;

/**
 * <p>
 *   CabCategory class has the getters and setters meant to hold the
 *   cab category object consists the fields that are related to
 *   cab category
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
@Table(name = "cab_category")
public class CabCategory extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cab_type")
    private String cabType;

    @Column(name = "initial_fare")
    private Double initialFare;

    @Column(name = "extra_fare_per_hour")
    private Double extraFarePerHour;

    @Column(name = "peak_hour_fare")
    private Double peakHourFare;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "cabCategory")
    List<Cab> cabs;
}
