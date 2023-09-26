package com.api.repairapi.controllers;

import com.api.repairapi.models.DeviceModelModel;
import com.api.repairapi.services.DeviceModelService;
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

public class DeviceModelControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DeviceModelService deviceModelService;

    @InjectMocks
    private DeviceModelController deviceModelController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceModelController).build();
    }

    @Test
    public void testGetDeviceModels() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<DeviceModelModel> deviceModels = new ArrayList<>();
        deviceModels.add(new DeviceModelModel());
        when(deviceModelService.getDeviceModels()).thenReturn(deviceModels);

        mockMvc.perform(get("/device-model")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(deviceModelService, times(1)).getDeviceModels();
    }

    @Test
    public void testGetDeviceModelById() throws Exception {
        Long deviceModelId = 1L;
        DeviceModelModel deviceModel = new DeviceModelModel();
        deviceModel.setId(deviceModelId);

        // Simular la respuesta del servicio
        when(deviceModelService.getDeviceModelById(deviceModelId)).thenReturn(Optional.of(deviceModel));

        mockMvc.perform(get("/device-model/{id}", deviceModelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(deviceModel.getId()));

        verify(deviceModelService, times(1)).getDeviceModelById(deviceModelId);
    }

    @Test
    public void testSaveDeviceModel() throws Exception {
        DeviceModelModel deviceModel = new DeviceModelModel();
        deviceModel.setId(1L);

        // Simular la respuesta del servicio
        when(deviceModelService.saveDeviceModel(any(DeviceModelModel.class))).thenReturn(deviceModel);

        mockMvc.perform(post("/device-model")
                        .content(asJsonString(deviceModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(deviceModel.getId()));

        verify(deviceModelService, times(1)).saveDeviceModel(any(DeviceModelModel.class));
    }

    @Test
    public void testUpdateDeviceModel() throws Exception {
        Long deviceModelId = 1L;
        DeviceModelModel deviceModel = new DeviceModelModel();
        deviceModel.setId(deviceModelId);

        // Simular la respuesta del servicio
        when(deviceModelService.updateDeviceModel(any(DeviceModelModel.class), eq(deviceModelId))).thenReturn(deviceModel);

        mockMvc.perform(put("/device-model/{id}", deviceModelId)
                        .content(asJsonString(deviceModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(deviceModel.getId()));

        verify(deviceModelService, times(1)).updateDeviceModel(any(DeviceModelModel.class), eq(deviceModelId));
    }

    @Test
    public void testDeleteDeviceModel_Success() throws Exception {
        Long deviceModelId = 1L;

        mockMvc.perform(delete("/device-model/{id}", deviceModelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deviceModelService, times(1)).deleteDeviceModel(deviceModelId);
    }

    @Test
    public void testDeleteDeviceModel_Exception() throws Exception {
        Long deviceModelId = 1L;

        // Simula una excepción en el servicio cuando se llama a deleteDeviceModel
        doThrow(new RuntimeException("Error al eliminar dispositivo")).when(deviceModelService).deleteDeviceModel(deviceModelId);

        mockMvc.perform(delete("/device-model/{id}", deviceModelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()); // Verifica que se retorne un código de estado 500
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
