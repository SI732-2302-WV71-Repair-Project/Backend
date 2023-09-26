package com.api.repairapi.services;

import com.api.repairapi.models.DeviceModelModel;
import com.api.repairapi.repositories.IDeviceModelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class DeviceModelServiceTest {

    @Mock
    private IDeviceModelRepository deviceModelRepository;

    @InjectMocks
    private DeviceModelService deviceModelService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDeviceModels() {
        when(deviceModelRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var deviceModels = deviceModelService.getDeviceModels();

        // Assert
        Assertions.assertNotNull(deviceModels);
    }

    @Test
    public void testGetDeviceModelById() {
        var mockDeviceModel = new DeviceModelModel();
        when(deviceModelRepository.findById(anyLong())).thenReturn(Optional.of(mockDeviceModel));

        // Act
        var deviceModelResult = deviceModelService.getDeviceModelById(1L);

        // Assert
        Assertions.assertTrue(deviceModelResult.isPresent());
    }

    @Test
    public void testSaveDeviceModel() {
        var mockDeviceModel = new DeviceModelModel();
        when(deviceModelRepository.save(any(DeviceModelModel.class))).thenReturn(mockDeviceModel);

        // Act
        var savedDeviceModel = deviceModelService.saveDeviceModel(mockDeviceModel);

        // Assert
        Assertions.assertNotNull(savedDeviceModel);
    }

    @Test
    public void testUpdateDeviceModel() {
        var mockDeviceModel = new DeviceModelModel();
        when(deviceModelRepository.findById(anyLong())).thenReturn(Optional.of(mockDeviceModel));

        when(deviceModelRepository.save(any(DeviceModelModel.class))).thenReturn(mockDeviceModel);

        // Act
        var updatedDeviceModel = deviceModelService.updateDeviceModel(mockDeviceModel, 1L);

        // Assert
        Assertions.assertNotNull(updatedDeviceModel);
    }

    @Test
    public void testDeleteDeviceModel() {
        doNothing().when(deviceModelRepository).deleteById(anyLong());

        // Act & Assert
        deviceModelService.deleteDeviceModel(1L);
    }

    @Test
    public void testDeleteDeviceModelWithException() {
        doThrow(new RuntimeException("Database error")).when(deviceModelRepository).deleteById(anyLong());

        deviceModelService.deleteDeviceModel(1L);
    }
}
