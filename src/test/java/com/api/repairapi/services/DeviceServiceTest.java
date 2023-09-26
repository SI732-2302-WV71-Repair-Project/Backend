package com.api.repairapi.services;

import com.api.repairapi.models.DeviceModel;
import com.api.repairapi.repositories.IDeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class DeviceServiceTest {

    @Mock
    private IDeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDevices() {
        // Mock the repository response
        when(deviceRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var devices = deviceService.getDevices();

        // Assert
        Assertions.assertNotNull(devices);
    }

    @Test
    public void testGetDeviceById() {
        var mockDevice = new DeviceModel();
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(mockDevice));

        // Act
        var deviceResult = deviceService.getDeviceById(1L);

        // Assert
        Assertions.assertTrue(deviceResult.isPresent());
    }

    @Test
    public void testSaveDevice() {
        var mockDevice = new DeviceModel();
        when(deviceRepository.save(any(DeviceModel.class))).thenReturn(mockDevice);

        // Act
        var savedDevice = deviceService.saveDevice(mockDevice);

        // Assert
        Assertions.assertNotNull(savedDevice);
    }

    @Test
    public void testUpdateDevice() {
        var mockDevice = new DeviceModel();
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(mockDevice));

        when(deviceRepository.save(any(DeviceModel.class))).thenReturn(mockDevice);

        // Act
        var updatedDevice = deviceService.updateDevice(mockDevice, 1L);

        // Assert
        Assertions.assertNotNull(updatedDevice);
    }

    @Test
    public void testDeleteDevice() {
        doNothing().when(deviceRepository).deleteById(anyLong());

        // Act & Assert
        deviceService.deleteDevice(1L);
    }

    @Test
    public void testDeleteDeviceWithException() {
        doThrow(new RuntimeException("Database error")).when(deviceRepository).deleteById(anyLong());

        deviceService.deleteDevice(1L);
    }
}
