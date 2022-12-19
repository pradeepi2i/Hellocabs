/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.repository;

import com.hellocabs.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *   Interface that provides abstract methods
 *   to perform CRUD operations for Ride entity
 *   to store data in database
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public interface RideRepository extends JpaRepository<Ride, Integer> {
}
