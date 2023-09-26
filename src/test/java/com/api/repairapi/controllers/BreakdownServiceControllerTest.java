package com.api.repairapi.controllers;
import com.api.repairapi.models.BreakdownServiceModel;
import com.api.repairapi.services.BreakdownServiceService;
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

public class BreakdownServiceControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BreakdownServiceService breakdownServiceService;

    @InjectMocks
    private BreakdownServiceController breakdownServiceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(breakdownServiceController).build();
    }

    @Test
    public void testGetBreakdownServices() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<BreakdownServiceModel> breakdownServices = new ArrayList<>();
        breakdownServices.add(new BreakdownServiceModel());
        when(breakdownServiceService.getBreakdownServices()).thenReturn(breakdownServices);

        mockMvc.perform(get("/breakdown-service")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(breakdownServiceService, times(1)).getBreakdownServices();
    }

    @Test
    public void testGetBreakdownServiceByID() throws Exception {
        Long breakdownServiceId = 1L;
        BreakdownServiceModel breakdownService = new BreakdownServiceModel();
        breakdownService.setId(breakdownServiceId);

        // Simular la respuesta del servicio
        when(breakdownServiceService.getBreakdownServiceById(breakdownServiceId)).thenReturn(Optional.of(breakdownService));

        mockMvc.perform(get("/breakdown-service/{id}", breakdownServiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(breakdownService.getId()));

        verify(breakdownServiceService, times(1)).getBreakdownServiceById(breakdownServiceId);
    }

    @Test
    public void testSaveBreakdownService() throws Exception {
        BreakdownServiceModel breakdownService = new BreakdownServiceModel();
        breakdownService.setId(1L);

        // Simular la respuesta del servicio
        when(breakdownServiceService.saveBreakdownService(any(BreakdownServiceModel.class))).thenReturn(breakdownService);

        mockMvc.perform(post("/breakdown-service")
                        .content(asJsonString(breakdownService))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(breakdownService.getId()));

        verify(breakdownServiceService, times(1)).saveBreakdownService(any(BreakdownServiceModel.class));
    }

    @Test
    public void testUpdateBreakdownService() throws Exception {
        Long breakdownServiceId = 1L;
        BreakdownServiceModel breakdownService = new BreakdownServiceModel();
        breakdownService.setId(breakdownServiceId);

        // Simular la respuesta del servicio
        when(breakdownServiceService.updateBreakdownService(any(BreakdownServiceModel.class), eq(breakdownServiceId))).thenReturn(breakdownService);

        mockMvc.perform(put("/breakdown-service/{id}", breakdownServiceId)
                        .content(asJsonString(breakdownService))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(breakdownService.getId()));

        verify(breakdownServiceService, times(1)).updateBreakdownService(any(BreakdownServiceModel.class), eq(breakdownServiceId));
    }

    @Test
    public void testDeleteBreakdownService() throws Exception {
        Long breakdownServiceId = 1L;

        mockMvc.perform(delete("/breakdown-service/{id}", breakdownServiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(breakdownServiceService, times(1)).deleteBreakdownService(breakdownServiceId);
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
