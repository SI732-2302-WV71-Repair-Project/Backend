package com.api.repairapi.controllers;

import com.api.repairapi.models.AppointmentModel;
import com.api.repairapi.services.AppointmentService;
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

public class AppointmentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void testGetAppointments() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<AppointmentModel> appointments = new ArrayList<>();
        appointments.add(new AppointmentModel());
        when(appointmentService.getAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(appointmentService, times(1)).getAppointments();
    }

    @Test
    public void testGetAppointmentById() throws Exception {
        Long appointmentId = 1L;
        AppointmentModel appointment = new AppointmentModel();
        appointment.setId(appointmentId);

        // Simular la respuesta del servicio
        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(Optional.of(appointment));

        mockMvc.perform(get("/appointment/{id}", appointmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(appointment.getId()));

        verify(appointmentService, times(1)).getAppointmentById(appointmentId);
    }

    @Test
    public void testSaveAppointment() throws Exception {
        AppointmentModel appointment = new AppointmentModel();
        appointment.setId(1L);

        // Simular la respuesta del servicio
        when(appointmentService.saveAppointment(any(AppointmentModel.class))).thenReturn(appointment);

        mockMvc.perform(post("/appointment")
                        .content(asJsonString(appointment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(appointment.getId()));

        verify(appointmentService, times(1)).saveAppointment(any(AppointmentModel.class));
    }

    @Test
    public void testUpdateAppointment() throws Exception {
        Long appointmentId = 1L;
        AppointmentModel appointment = new AppointmentModel();
        appointment.setId(appointmentId);

        // Simular la respuesta del servicio
        when(appointmentService.updateAppointment(any(AppointmentModel.class), eq(appointmentId))).thenReturn(appointment);

        mockMvc.perform(put("/appointment/{id}", appointmentId)
                        .content(asJsonString(appointment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(appointment.getId()));

        verify(appointmentService, times(1)).updateAppointment(any(AppointmentModel.class), eq(appointmentId));
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        Long appointmentId = 1L;

        mockMvc.perform(delete("/appointment/{id}", appointmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(appointmentService, times(1)).deleteAppointment(appointmentId);
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
