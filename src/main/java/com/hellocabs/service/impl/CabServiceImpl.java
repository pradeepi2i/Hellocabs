/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CabMapper;
import com.hellocabs.model.Cab;
import com.hellocabs.repository.CabRepository;
import com.hellocabs.service.CabService;
import com.hellocabs.validation.HelloCabsValidation;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h> CabServiceImpl </h>
 * <p>
 *   Class is used to define the implemented methods from CabService interface
 *   and used to maintain the cab Details given by the user with help of cabDto object
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 *
 */
@Service
@RequiredArgsConstructor
public class CabServiceImpl implements CabService {

    private static final Logger logger = LoggerConfiguration.getInstance(HelloCabsConstants.CAB_SERVICE_CLASS);
    private final CabRepository cabRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * <p>
     *   Method used to create Cab  details by using CabDto Object,
     *   convert to cab object then pass to database to create
     *   new cab id to store in it
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @return {@link String}returns cab details object
     *
     */
    @Override
    public CabDto createCab(CabDto cabDto) {

        if (HelloCabsValidation.isValidMobileNumber(cabDto.getMobileNumber())
                && !cabRepository.existsByMobileNumberOrEmail(
                        cabDto.getMobileNumber(), cabDto.getEmail())) {
            cabDto.setPassword(passwordEncoder.encode(cabDto.getPassword()));
            Cab cab = cabRepository.save(CabMapper.convertCabDtoToCab(cabDto));
            logger.info(HelloCabsConstants.CAB_CREATED + cab.getId());
            return CabMapper.convertCabToCabDto(cab);
        }
        logger.info(HelloCabsConstants.CAB_NOT_CREATED);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_CREATED
                + HelloCabsConstants.CUSTOMER_ALREADY_EXIST);
    }

    /**
     * <p>
     *   Method used to update Cab details by using Cab Object
     *   and id to check and update the existing cab details
     * </p>
     *
     * @param cab {@link Cab} object with required details
     * @param id {@link Integer}used to check the exists details in database
     * @return {@link Cab}returns status of the cab details
     *
     */
    public Cab updateCabDetailsById(Integer id, Cab cab) {

        if (cabRepository.existsByIdAndIsActive(id, true)) {
            return cabRepository.save(cab);
        }
        logger.info(HelloCabsConstants.CAB_NOT_UPDATED);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_UPDATED);
    }

    /**
     * <p>
     *   Method used to update Cab  details by using CabDto Object
     *   and id to check and update the existing cab details
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @param id {@link Integer}used to check the exists details in database
     * @return {@link String}returns status of the cab details
     *
     */

    public CabDto updateCabById(Integer id, CabDto cabDto) {

        if (HelloCabsValidation.isValidMobileNumber(cabDto.getMobileNumber())
                && cabRepository.existsByIdAndIsActive(id, true)) {
            Cab cab = cabRepository.save(CabMapper.convertCabDtoToCab(cabDto));
            logger.info(HelloCabsConstants.CAB_UPDATED + cab.getId());
            return CabMapper.convertCabToCabDto(cab);
        }
        logger.info(HelloCabsConstants.CAB_NOT_UPDATED);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     * This method is to update cab details by getting and converting the cab
     * dto to cab entity and saves into the cab repository and returns
     * status message only if the given id is already exits otherwise
     * it throws exception.
     * </p>
     *
     * @param cabDto {@link CabDto} for which the cab to be updated is given
     * @return {@link CabDto} updated cabDto
     */
    @Override
    public CabDto updateCab(CabDto cabDto) {

        if (HelloCabsValidation.isValidMobileNumber(cabDto.getMobileNumber())
                && cabRepository.existsById(cabDto.getId())) {
            Cab cab = cabRepository.save(CabMapper.convertCabDtoToCab(cabDto));
            logger.info(HelloCabsConstants.CAB_UPDATED + cab.getId());
            return CabMapper.convertCabToCabDto(cab);
        }
        logger.info(HelloCabsConstants.CAB_NOT_UPDATED);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     *   Method used to get Cab  details by using CabId if exists
     *   or returns null to controller
     * </p>
     *
     * @param id {@link Integer}used to get details from server if exists
     * @return {@link CabDto}returns cab details object by using cabId
     *
     */
    @Override
    public CabDto displayCabDetailsById(Integer id) {
        Cab cab = cabRepository.findByIdAndIsActive(id,true);

        if (null != cab) {
            logger.info(HelloCabsConstants.CAB_FOUND + cab);
            return CabMapper.convertCabToCabDto(cab);
        }
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     *   Method used to get Cab  details by using CabId if exists
     *   or returns null to controller
     * </p>
     *
     * @param id {@link Integer}used to get details from server if exists
     * @return {@link Cab}returns cab details object by using cabId
     *
     */
    public Cab searchCabById(Integer id) {
        Cab cab = cabRepository.findByIdAndIsActive(id,true);

        if (null != cab) {
            return cab;
        }
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     *   Method used to Delete Cab details by using CabId
     *   if it exists returns the status of the cabId or returns the id not found
     * </p>
     *
     * @param id {@link Integer}used to delete details if exists
     * @return {String}returns Status of the cab id
     *
     */
    @Override
    public String deleteCabDetailsById(Integer id) {
        Cab cab = cabRepository.findByIdAndIsActive(id,true);

        if (null != cab) {
            cab.setActive(false);
            cabRepository.save(cab);
            logger.info(HelloCabsConstants.CAB_DELETED + cab.getId());
            return HelloCabsConstants.CAB_DELETED;
        }
        logger.error(HelloCabsConstants.CAB_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     *   Method used to get All Cab details  from server which is
     *   implemented from EmployeeService and pass to the controller
     * </p>
     *
     * @return {@link List<CabDto>}returns all the cab details in the form of list
     *
     */
    @Override
    public List<CabDto> showAllCabDetails() {
        return cabRepository.findAllByIsActive(true).stream()
                .map(CabMapper :: convertCabToCabDto).collect(Collectors.toList());
    }

    /**
     * <p>
     *   Implement this method used to find cab object by
     *   cab driver's mobile number
     * </p>
     *
     * @param mobileNumber {@link Long}  mobile number to be searched
     * @return {@link Cab} returns cab object which contains the given mobile number
     *
     */
    @Override
    public Cab findCabByMobileNumber(long mobileNumber) {
        return cabRepository.findCabByMobileNumber(mobileNumber);
    }
}
