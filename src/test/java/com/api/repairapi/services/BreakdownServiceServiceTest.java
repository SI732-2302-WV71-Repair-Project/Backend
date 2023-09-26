package com.api.repairapi.services;

import com.api.repairapi.models.BreakdownServiceModel;
import com.api.repairapi.repositories.IBreakdownServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class BreakdownServiceServiceTest {

    @Mock
    private IBreakdownServiceRepository breakdownServiceRepository;

    @InjectMocks
    private BreakdownServiceService breakdownService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBreakdownServices() {
        when(breakdownServiceRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var breakdownServices = breakdownService.getBreakdownServices();

        // Assert
        Assertions.assertNotNull(breakdownServices);
    }

    @Test
    public void testGetBreakdownServiceById() {
        var mockBreakdownService = new BreakdownServiceModel();
        when(breakdownServiceRepository.findById(anyLong())).thenReturn(Optional.of(mockBreakdownService));

        // Act
        var breakdownServiceResult = breakdownService.getBreakdownServiceById(1L);

        // Assert
        Assertions.assertTrue(breakdownServiceResult.isPresent());
    }

    @Test
    public void testSaveBreakdownService() {
        var mockBreakdownService = new BreakdownServiceModel();
        when(breakdownServiceRepository.save(any(BreakdownServiceModel.class))).thenReturn(mockBreakdownService);

        // Act
        var savedBreakdownService = breakdownService.saveBreakdownService(mockBreakdownService);

        // Assert
        Assertions.assertNotNull(savedBreakdownService);
    }

    @Test
    public void testUpdateBreakdownService() {
        var mockBreakdownService = new BreakdownServiceModel();
        when(breakdownServiceRepository.findById(anyLong())).thenReturn(Optional.of(mockBreakdownService));

        when(breakdownServiceRepository.save(any(BreakdownServiceModel.class))).thenReturn(mockBreakdownService);

        // Act
        var updatedBreakdownService = breakdownService.updateBreakdownService(mockBreakdownService, 1L);

        // Assert
        Assertions.assertNotNull(updatedBreakdownService);
    }

    @Test
    public void testDeleteBreakdownService() {
        doNothing().when(breakdownServiceRepository).deleteById(anyLong());

        // Act & Assert
        breakdownService.deleteBreakdownService(1L);
    }

    @Test
    public void testDeleteBreakdownServiceWithException() {
        doThrow(new RuntimeException("Database error")).when(breakdownServiceRepository).deleteById(anyLong());

        breakdownService.deleteBreakdownService(1L);
    }
}
