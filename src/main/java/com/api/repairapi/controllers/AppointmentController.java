package com.api.repairapi.controllers;

import com.api.repairapi.models.AppointmentModel;
import com.api.repairapi.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ArrayList<AppointmentModel> getAppointments(){
        return this.appointmentService.getAppointments();
    }

    @GetMapping(path = "/{id}")
    public Optional<AppointmentModel>  getAppointmentById(@PathVariable Long id){
        return this.appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public AppointmentModel saveAppointment(@RequestBody AppointmentModel appointment){
        return this.appointmentService.saveAppointment(appointment);
    }

    @PutMapping(path = "/{id}")
    public AppointmentModel updateAppointment(@RequestBody AppointmentModel request, @PathVariable Long id){
        return this.appointmentService.updateAppointment(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAppointment(@PathVariable Long id){
        this.appointmentService.deleteAppointment(id);
    }
}
