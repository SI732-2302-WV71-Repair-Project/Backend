package com.api.repairapi.controllers;

import com.api.repairapi.models.TechnicianModel;
import com.api.repairapi.services.TechnicianService;
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


public class TechnicianControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TechnicianService technicianService;

    @InjectMocks
    private TechnicianController technicianController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(technicianController).build();
    }

    @Test
    public void testGetTechnicians() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<TechnicianModel> technicians = new ArrayList<>();
        technicians.add(new TechnicianModel());
        when(technicianService.getTechnicians()).thenReturn(technicians);

        mockMvc.perform(get("/technician")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(technicianService, times(1)).getTechnicians();
    }

    @Test
    public void testGetTechnicianById() throws Exception {
        Long technicianId = 1L;
        TechnicianModel technician = new TechnicianModel();
        technician.setId(technicianId);

        // Simular la respuesta del servicio
        when(technicianService.getTechnicianById(technicianId)).thenReturn(Optional.of(technician));

        mockMvc.perform(get("/technician/{id}", technicianId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(technician.getId()));

        verify(technicianService, times(1)).getTechnicianById(technicianId);
    }

    @Test
    public void testSaveTechnician() throws Exception {
        TechnicianModel technician = new TechnicianModel();
        technician.setId(1L);

        // Simular la respuesta del servicio
        when(technicianService.saveTechnician(any(TechnicianModel.class))).thenReturn(technician);

        mockMvc.perform(post("/technician")
                        .content(asJsonString(technician))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(technician.getId()));

        verify(technicianService, times(1)).saveTechnician(any(TechnicianModel.class));
    }

    @Test
    public void testUpdateTechnician() throws Exception {
        Long technicianId = 1L;
        TechnicianModel technician = new TechnicianModel();
        technician.setId(technicianId);

        // Simular la respuesta del servicio
        when(technicianService.updateTechnician(any(TechnicianModel.class), eq(technicianId))).thenReturn(technician);

        mockMvc.perform(put("/technician/{id}", technicianId)
                        .content(asJsonString(technician))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(technician.getId()));

        verify(technicianService, times(1)).updateTechnician(any(TechnicianModel.class), eq(technicianId));
    }

    @Test
    public void testDeleteTechnician() throws Exception {
        Long technicianId = 1L;

        mockMvc.perform(delete("/technician/{id}", technicianId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(technicianService, times(1)).deleteTechnician(technicianId);
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
