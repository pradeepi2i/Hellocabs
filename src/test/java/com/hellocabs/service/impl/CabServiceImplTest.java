package com.hellocabs.service.impl;

import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Cab;
import com.hellocabs.repository.CabRepository;
import org.junit.jupiter.api.BeforeEach;
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
    CabRepository cabRepository;
    Cab cab;

    @BeforeEach
    void setup() {
        cab = new Cab();
        cab.setId(1);
    }

    @Test
    void createCab() {
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals(cab, cabRepository.save(cab));
    }

    @Test
    void getCabSuccess() {
        when(cabRepository.getById(1)).thenReturn(cab);
        assertEquals(1,cabRepository.getById(1).getId());
    }

    @Test
    void getCabFailure() {
        when(cabRepository.getById(0)).thenThrow(new HelloCabsException("Cab"));
        Throwable exception = assertThrows(HelloCabsException.class, () -> cabRepository.getById(0));
        assertEquals("Cab", exception.getMessage());
    }

    @Test
    void getAllCabsSuccess() {
        when(cabRepository.findAll()).thenReturn(Stream.of(cab, cab).collect(Collectors.toList()));
        assertEquals(2, cabRepository.findAll().size());
    }

    @Test
    void updateCabSuccess() {
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals(cab, cabRepository.save(cab));
    }

    @Test
    void UpdateCabNonExistedId() {
        Cab  anotherCab = new Cab();
        anotherCab.setId(2);
        when(cabRepository.save(cab)).thenReturn(cab);
        assertNotEquals(cab, cabRepository.save(anotherCab), "No Id found");
    }

    @Test
    void updateCabStatusSuccess() {
        cab.setCabStatus("Available");
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals("Available", cabRepository.save(cab).getCabStatus());
        assertNotEquals(cab, null);
        // assertEquals(null, doThrow(new NoSuchElementException("No such id found")));
    }

//    @Test
//    void updateRideStatusFailure() {
//        when(cabRepository.save(Cab)).thenReturn(Cab);
//        assertEquals(null, doThrow(new NoSuchElementException("No such id found")));
//    }


    @Test
    void deleteCab() {
        cabRepository.deleteById(1);
        verify(cabRepository, times(1)).deleteById(1);
    }


    @Test
    void updateCabStatusEmptyString() {
        cab.setCabStatus("");
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals("", cabRepository.save(cab).getCabStatus());
    }

    @Test
    void updateCabStatusNull() {
        cab.setCabStatus(null);
        when(cabRepository.save(cab)).thenReturn(cab);
        assertEquals(null, cabRepository.save(cab).getCabStatus());
        //assertEquals(null, doThrow(new NoSuchElementException("Can't assign null to status")));
    }


}
