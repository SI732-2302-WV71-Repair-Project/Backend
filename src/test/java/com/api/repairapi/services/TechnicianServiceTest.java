package com.api.repairapi.services;

import com.api.repairapi.models.TechnicianModel;
import com.api.repairapi.repositories.ITechnicianRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class TechnicianServiceTest {

    @Mock
    private ITechnicianRepository technicianRepository;

    @InjectMocks
    private TechnicianService technicianService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTechnicians() {
        when(technicianRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var technicians = technicianService.getTechnicians();

        // Assert
        Assertions.assertNotNull(technicians);
    }

    @Test
    public void testGetTechnicianById() {
        var mockTechnician = new TechnicianModel();
        when(technicianRepository.findById(anyLong())).thenReturn(Optional.of(mockTechnician));

        // Act
        var technicianResult = technicianService.getTechnicianById(1L);

        // Assert
        Assertions.assertTrue(technicianResult.isPresent());
    }

    @Test
    public void testSaveTechnician() {
        var mockTechnician = new TechnicianModel();
        when(technicianRepository.save(any(TechnicianModel.class))).thenReturn(mockTechnician);

        // Act
        var savedTechnician = technicianService.saveTechnician(mockTechnician);

        // Assert
        Assertions.assertNotNull(savedTechnician);
    }

    @Test
    public void testUpdateTechnician() {
        var mockTechnician = new TechnicianModel();
        when(technicianRepository.findById(anyLong())).thenReturn(Optional.of(mockTechnician));

        when(technicianRepository.save(any(TechnicianModel.class))).thenReturn(mockTechnician);

        // Act
        var updatedTechnician = technicianService.updateTechnician(mockTechnician, 1L);

        // Assert
        Assertions.assertNotNull(updatedTechnician);
    }

    @Test
    public void testDeleteTechnician() {
        doNothing().when(technicianRepository).deleteById(anyLong());

        // Act & Assert
        technicianService.deleteTechnician(1L);
    }

    @Test
    public void testDeleteTechnicianWithException() {
        doThrow(new RuntimeException("Database error")).when(technicianRepository).deleteById(anyLong());

        technicianService.deleteTechnician(1L);
    }
}
