/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service;

import com.hellocabs.dto.CabDto;
import com.hellocabs.model.Cab;

import java.util.List;

/**
 * <h> CabService </h>
 * <p>
 * Interface is used to implement the abstract methods for the cab details
 *
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 *
 */
public interface CabService {

    /**
     * <p>
     *   Method used to create Cab details by using CabDto Object,
     *   convert to cab object then pass to database to create
     *   new cab id to store in it and returns message with
     *   cab id if the mobile number or email id already
     *   exits otherwise throws exception
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @return {@link String}returns cab details object
     *
     */
    CabDto createCab(CabDto cabDto);

    /**
     * <p>
     *   This method is to update cab Details by getting the cab
     *   entity and saves into the cab repository and returns
     *   updated cab only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab to be updated
     * @param cab {@link Cab} for which the cab to be updated is given
     * @return {@link Cab} updated customer is returned
     *
     */
    Cab updateCabDetailsById(Integer id, Cab cab);

    /**
     * <p>
     *   This method is to update specific cab details by getting and converting the cab
     *   dto to cab entity and saves into the cab repository and returns
     *   status message only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab to be updated
     * @param cabDto {@link CabDto} for which the cab to be updated is given
     * @return {@link CabDto} updated cabDto
     *
     */
    CabDto updateCabById(Integer id, CabDto cabDto);

    /**
     * <p>
     *   This method is to update cab details by getting and converting the cab
     *   dto to cab entity and saves into the cab repository and returns
     *   status message only if the given id is already exits otherwise
     *   it throws exception.
     * </p>
     *
     * @param cabDto {@link CabDto} for which the cab to be updated is given
     * @return {@link CabDto} updated cabDto
     *
     */
    CabDto updateCab(CabDto cabDto);

    /**
     * <p>
     *   This method is to get cab Details by getting cab id
     *   and retrieves the cab from the cab repository and
     *   returns searched cab dto only if the given id
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab need to be searched is given
     * @return {@link CabDto} searched cab is returned
     *
     */
    CabDto displayCabDetailsById(Integer id);

    /**
     * <p>
     *   This method is to get cab Details by getting cab id
     *   and retrieves the cab from the cab repository and
     *   returns searched cab only if the given id
     *   already exits otherwise it throws exception.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab need to be searched is given
     * @return {@link Cab} searched cab is returned
     *
     */
    Cab searchCabById(Integer id);

    /**
     * <p>
     *   Method used to Delete Cab details by using CabId and returns
     *   the deleted status of the cab if the given id is present otherwise
     *   throws exception
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab need to be deleted is given
     * @return {@link String} returns the message if the cab is deleted
     *
     */
    String deleteCabDetailsById(Integer id);

    /**
     * <p>
     *   Method used to get All Cab details  from server which is
     *   implemented from EmployeeService and pass to the controller
     * </p>
     *
     * @return {@link List<CabDto>}returns all the cab details in the form of list
     *
     */
    List<CabDto> showAllCabDetails();

    /**
     * <p>
     *   This method is to get cab Details by getting cab mobile number
     *   and retrieves the cab from the cab repository and returns
     *   searched cab if the given mobile number already
     *   exits otherwise it throws exception.
     * </p>
     *
     * @param mobileNumber {@link Integer} for which the mobile number of the cab
     *                                   need to be searched is given
     * @return {@link Cab} searched cab is returned
     *
     */
    Cab findCabByMobileNumber(long mobileNumber);
}
