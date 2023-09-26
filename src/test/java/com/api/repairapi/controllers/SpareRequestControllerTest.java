package com.api.repairapi.controllers;

import com.api.repairapi.models.SpareRequestModel;
import com.api.repairapi.services.SpareRequestService;
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

public class SpareRequestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SpareRequestService spareRequestService;

    @InjectMocks
    private SpareRequestController spareRequestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(spareRequestController).build();
    }

    @Test
    public void testGetSpareRequests() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<SpareRequestModel> spareRequests = new ArrayList<>();
        spareRequests.add(new SpareRequestModel());
        when(spareRequestService.getSpareRequests()).thenReturn(spareRequests);

        mockMvc.perform(get("/spare-request")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(spareRequestService, times(1)).getSpareRequests();
    }

    @Test
    public void testGetSpareRequestById() throws Exception {
        Long spareRequestId = 1L;
        SpareRequestModel spareRequest = new SpareRequestModel();
        spareRequest.setId(spareRequestId);

        // Simular la respuesta del servicio
        when(spareRequestService.getSpareRequestById(spareRequestId)).thenReturn(Optional.of(spareRequest));

        mockMvc.perform(get("/spare-request/{id}", spareRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(spareRequest.getId()));

        verify(spareRequestService, times(1)).getSpareRequestById(spareRequestId);
    }

    @Test
    public void testSaveSpareRequest() throws Exception {
        SpareRequestModel spareRequest = new SpareRequestModel();
        spareRequest.setId(1L);

        // Simular la respuesta del servicio
        when(spareRequestService.saveSpareRequest(any(SpareRequestModel.class))).thenReturn(spareRequest);

        mockMvc.perform(post("/spare-request")
                        .content(asJsonString(spareRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(spareRequest.getId()));

        verify(spareRequestService, times(1)).saveSpareRequest(any(SpareRequestModel.class));
    }

    @Test
    public void testUpdateSpareRequestById() throws Exception {
        Long spareRequestId = 1L;
        SpareRequestModel spareRequest = new SpareRequestModel();
        spareRequest.setId(spareRequestId);

        // Simular la respuesta del servicio
        when(spareRequestService.updateSpareRequestById(any(SpareRequestModel.class), eq(spareRequestId))).thenReturn(spareRequest);

        mockMvc.perform(put("/spare-request/{id}", spareRequestId)
                        .content(asJsonString(spareRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(spareRequest.getId()));

        verify(spareRequestService, times(1)).updateSpareRequestById(any(SpareRequestModel.class), eq(spareRequestId));
    }

    @Test
    public void testDeleteSpareRequestById() throws Exception {
        Long spareRequestId = 1L;

        mockMvc.perform(delete("/spare-request/{id}", spareRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(spareRequestService, times(1)).deleteSpareRequestById(spareRequestId);
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
