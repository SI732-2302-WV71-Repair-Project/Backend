package com.api.repairapi.services;

import com.api.repairapi.models.TechnicianModel;
import com.api.repairapi.repositories.ITechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private ITechnicianRepository technicianRepository;

    public ArrayList<TechnicianModel> getTechnicians(){
        return (ArrayList<TechnicianModel>) this.technicianRepository.findAll();
    }

    public Optional<TechnicianModel> getTechnicianById(Long id){
        return this.technicianRepository.findById(id);
    }

    public TechnicianModel saveTechnician(TechnicianModel technician){
        return this.technicianRepository.save(technician);
    }

    public TechnicianModel updateTechnician(TechnicianModel request, Long id){
        TechnicianModel technicianModel = this.technicianRepository.findById(id).get();

        technicianModel.setName(request.getName());
        technicianModel.setUser(request.getUser());

        return this.technicianRepository.save(technicianModel);
    }

    public void deleteTechnician(Long id){
        try{
            this.technicianRepository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
