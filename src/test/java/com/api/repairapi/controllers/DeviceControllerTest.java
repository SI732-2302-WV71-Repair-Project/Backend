package com.api.repairapi.controllers;
import com.api.repairapi.models.DeviceModel;
import com.api.repairapi.services.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeviceControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    public void testGetDevices() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<DeviceModel> devices = new ArrayList<>();
        devices.add(new DeviceModel());
        when(deviceService.getDevices()).thenReturn(devices);

        mockMvc.perform(get("/device")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(deviceService, times(1)).getDevices();
    }

    @Test
    public void testGetDeviceById() throws Exception {
        Long deviceId = 1L;
        DeviceModel device = new DeviceModel();
        device.setId(deviceId);

        // Simular la respuesta del servicio
        when(deviceService.getDeviceById(deviceId)).thenReturn(Optional.of(device));

        mockMvc.perform(get("/device/{id}", deviceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(device.getId()));

        verify(deviceService, times(1)).getDeviceById(deviceId);
    }

    @Test
    public void testSaveDeviceModel() throws Exception {
        DeviceModel device = new DeviceModel();
        device.setId(1L);

        // Simular la respuesta del servicio
        when(deviceService.saveDevice(any(DeviceModel.class))).thenReturn(device);

        mockMvc.perform(post("/device")
                        .content(asJsonString(device))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(device.getId()));

        verify(deviceService, times(1)).saveDevice(any(DeviceModel.class));
    }

    @Test
    public void testUpdateDeviceMode() throws Exception {
        Long deviceId = 1L;
        DeviceModel device = new DeviceModel();
        device.setId(deviceId);

        // Simular la respuesta del servicio
        when(deviceService.updateDevice(any(DeviceModel.class), eq(deviceId))).thenReturn(device);

        mockMvc.perform(put("/device/{id}", deviceId)
                        .content(asJsonString(device))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(device.getId()));

        verify(deviceService, times(1)).updateDevice(any(DeviceModel.class), eq(deviceId));
    }

    @Test
    public void testDeleteDeviceModel() throws Exception {
        Long deviceId = 1L;

        mockMvc.perform(delete("/device/{id}", deviceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deviceService, times(1)).deleteDevice(deviceId);
    }

    // Utilidad para convertir objetos a JSON (puede ser la misma que usaste en otras pruebas)
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
