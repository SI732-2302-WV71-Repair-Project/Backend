package com.api.repairapi.services;

import com.api.repairapi.models.AppointmentModel;
import com.api.repairapi.repositories.IAppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAppointments() {
        when(appointmentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        ArrayList<AppointmentModel> appointments = appointmentService.getAppointments();

        // Assert
        Assertions.assertNotNull(appointments);
    }

    @Test
    public void testGetAppointmentById() {
        AppointmentModel mockAppointment = new AppointmentModel();
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(mockAppointment));

        // Act
        Optional<AppointmentModel> appointment = appointmentService.getAppointmentById(1L);

        // Assert
        Assertions.assertTrue(appointment.isPresent());
    }

    @Test
    public void testSaveAppointment() {
        AppointmentModel mockAppointment = new AppointmentModel();
        when(appointmentRepository.save(any(AppointmentModel.class))).thenReturn(mockAppointment);

        // Act
        AppointmentModel savedAppointment = appointmentService.saveAppointment(mockAppointment);

        // Assert
        Assertions.assertNotNull(savedAppointment);
    }

    @Test
    public void testUpdateAppointment() {
        AppointmentModel mockAppointment = new AppointmentModel();
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(mockAppointment));

        when(appointmentRepository.save(any(AppointmentModel.class))).thenReturn(mockAppointment);

        // Act
        AppointmentModel updatedAppointment = appointmentService.updateAppointment(mockAppointment, 1L);

        // Assert
        Assertions.assertNotNull(updatedAppointment);
    }

    @Test
    public void testDeleteAppointment() {
        doNothing().when(appointmentRepository).deleteById(anyLong());

        // Act & Assert
        appointmentService.deleteAppointment(1L);
    }

    @Test
    public void testDeleteAppointmentWithException() {
        doThrow(new RuntimeException("Database error")).when(appointmentRepository).deleteById(anyLong());

        appointmentService.deleteAppointment(1L);
    }
}
