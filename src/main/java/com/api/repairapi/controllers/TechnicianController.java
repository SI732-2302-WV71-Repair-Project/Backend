package com.api.repairapi.controllers;

import com.api.repairapi.models.TechnicianModel;
import com.api.repairapi.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/technician")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping
    public ArrayList<TechnicianModel> getTechnicians(){
        return this.technicianService.getTechnicians();
    }

    @GetMapping(path = "/{id}")
    public Optional<TechnicianModel> getTechnicianById(@PathVariable Long id){
        return this.technicianService.getTechnicianById(id);
    }

    @PostMapping
    public TechnicianModel saveTechnician(@RequestBody TechnicianModel technician){
        return this.technicianService.saveTechnician(technician);
    }

    @PutMapping(path = "/{id}")
    public TechnicianModel updateTechnician(@RequestBody TechnicianModel request, @PathVariable Long id ){
        return this.technicianService.updateTechnician(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTechnician(@PathVariable Long id){
        this.technicianService.deleteTechnician(id);
    }

}
