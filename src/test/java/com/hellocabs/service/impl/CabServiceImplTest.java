package com.hellocabs.service.impl;

import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Cab;
import com.hellocabs.repository.CabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CabServiceImplTest {

    @Mock
    private CabRepository cabRepository;
    private Cab cab;
    private Cab savedCab;

    @BeforeEach
    public void setup() {
        cab = new Cab();
        cab.setId(1);
        cab.setCabNumber(null);
        cab.setDriverName(null);
        cab.setGender(null);
        cab.setMobileNumber(null);
        cab.setDriverRating(null);
        cab.setCabStatus(null);
        cab.setEmail(null);
        cab.setLicenseNumber(null);
        cab.setCarModel(null);
    }

    /**
     * Used this method to check that the given input and the returned
     * object are equal if they are not then the testcase will fail
     */
    @Test
    @DisplayName("Check the given object and saved object are equal")
    public void createCab() {
        when(cabRepository.save(cab)).thenReturn(cab);
        savedCab = cabRepository.save(cab);
        assertEquals(cab, savedCab);
    }

    /**
     * Test whether the given id has been on repository
     * only then the test case should succeed
     */
    @Test
    @DisplayName("Fetch Success:Get cab using id")
    public void getCabSuccess() {
        when(cabRepository.getById(1)).thenReturn(cab);
        assertEquals(1,cabRepository.getById(1).getId());
    }

    /**
     * Test whether the id is a valid one or not, if not then throws exception
     * {@link HelloCabsException}
     */
    @Test
    @DisplayName("Fetch Failure: Due to invalid id")
    public void getCabFailure() {
        when(cabRepository.getById(0)).thenThrow(new HelloCabsException("Invalid cab id"));
        Throwable exception = assertThrows(HelloCabsException.class, () -> cabRepository.getById(0));
        assertEquals("cab", exception.getMessage());
    }

    /**
     * The number of cab object in the list and no.of object in the
     * repository must be equal then only test case pass
     */
    @Test
    @DisplayName("Fetch All cabs Success")
    public void getAllCabsSuccess() {
        when(cabRepository.findAll()).thenReturn(Stream.of(cab, cab).collect(Collectors.toList()));
        assertEquals(2, cabRepository.findAll().size());
    }

    /**
     * Update the cab details only when the cab id is valid and succeed the test case
     */
    @Test
    @DisplayName("Update Cab Success")
    public void updateCabSuccess() {
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals(cab, cabRepository.save(cab));
    }

    /**
     * Test case succeeds if the user try to update the details
     * of the ride which is not existed in database
     */
    @Test
    @DisplayName("Update Cab Failure : Cab update failed due to non-existed id")
    public void UpdateCabNonExistedId() {
        Cab  anotherCab = new Cab();
        anotherCab.setId(2);
        when(cabRepository.save(cab)).thenReturn(cab);
        assertNotEquals(cab, cabRepository.save(anotherCab), "No Id found");
    }

    /**
     * Update the status info of the cab only when the cab status
     * is a valid one and fails if an entered status value is invalid
     */
    @Test
    @DisplayName("Update status Success")
    public void updateCabStatusSuccess() {
        cab.setCabStatus("Available");
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals("Available", cabRepository.save(cab).getCabStatus());
        assertNotEquals(cab, null);
    }

    /**
     * whenever the user wants to deregister from the enterprise
     * and make sure that the id is a valid one
     */
    @Test
    @DisplayName("Delete ride")
    public void deleteCab() {
        cabRepository.deleteById(1);
        verify(cabRepository, times(1)).deleteById(1);
    }

    /**
     * When driver passed a empty value to update the status of cab
     */
    @Test
    @DisplayName("Update status Failure : Status is empty")
    public void updateCabStatusEmptyString() {
        cab.setCabStatus("");
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals("", cabRepository.save(cab).getCabStatus());
    }

    /**
     * When user passed a null value to update the status of cab
     */
    @Test
    @DisplayName("Update status Failure : null is passed as a status")
    public void updateCabStatusNull() {
        cab.setCabStatus(null);
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals(null, cabRepository.save(cab).getCabStatus());
    }
}
