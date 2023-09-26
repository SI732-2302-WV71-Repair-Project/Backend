package com.api.repairapi.services;

import com.api.repairapi.models.SpareRequestModel;
import com.api.repairapi.repositories.ISpareRequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class SpareRequestServiceTest {

    @Mock
    private ISpareRequestRepository spareRequestRepository;

    @InjectMocks
    private SpareRequestService spareRequestService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSpareRequests() {
        when(spareRequestRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var spareRequests = spareRequestService.getSpareRequests();

        // Assert
        Assertions.assertNotNull(spareRequests);
    }

    @Test
    public void testGetSpareRequestById() {
        var mockSpareRequest = new SpareRequestModel();
        when(spareRequestRepository.findById(anyLong())).thenReturn(Optional.of(mockSpareRequest));

        // Act
        var spareRequestResult = spareRequestService.getSpareRequestById(1L);

        // Assert
        Assertions.assertTrue(spareRequestResult.isPresent());
    }

    @Test
    public void testSaveSpareRequest() {
        var mockSpareRequest = new SpareRequestModel();
        when(spareRequestRepository.save(any(SpareRequestModel.class))).thenReturn(mockSpareRequest);

        // Act
        var savedSpareRequest = spareRequestService.saveSpareRequest(mockSpareRequest);

        // Assert
        Assertions.assertNotNull(savedSpareRequest);
    }

    @Test
    public void testUpdateSpareRequestById() {
        var mockSpareRequest = new SpareRequestModel();
        when(spareRequestRepository.findById(anyLong())).thenReturn(Optional.of(mockSpareRequest));
        when(spareRequestRepository.save(any(SpareRequestModel.class))).thenReturn(mockSpareRequest);

        // Act
        var updatedSpareRequest = spareRequestService.updateSpareRequestById(mockSpareRequest, 1L);

        // Assert
        Assertions.assertNotNull(updatedSpareRequest);
    }

    @Test
    public void testDeleteSpareRequestById() {
        doNothing().when(spareRequestRepository).deleteById(anyLong());

        // Act & Assert
        spareRequestService.deleteSpareRequestById(1L);
    }

    @Test
    public void testDeleteSpareRequestByIdWithException() {
        doThrow(new RuntimeException("Database error")).when(spareRequestRepository).deleteById(anyLong());
        spareRequestService.deleteSpareRequestById(1L);
    }
}
