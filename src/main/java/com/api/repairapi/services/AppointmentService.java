package com.api.repairapi.services;

import com.api.repairapi.models.AppointmentModel;
import com.api.repairapi.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    public ArrayList<AppointmentModel> getAppointments(){
        return (ArrayList<AppointmentModel>) this.appointmentRepository.findAll();
    }

    public Optional<AppointmentModel> getAppointmentById(Long id){
        return this.appointmentRepository.findById(id);
    }

    public AppointmentModel saveAppointment(AppointmentModel appointment){
        return this.appointmentRepository.save(appointment);
    }

    public AppointmentModel updateAppointment(AppointmentModel request, Long id){
        AppointmentModel appointment = this.appointmentRepository.findById(id).get();

        appointment.setSchedule_date(request.getSchedule_date());
        appointment.setState(request.getState());
        appointment.setClient(request.getClient());

        return this.appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        try {
            this.appointmentRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
