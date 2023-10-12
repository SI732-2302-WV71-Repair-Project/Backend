package com.api.repairapi.services;

import com.api.repairapi.models.DiagnosticModel;
import com.api.repairapi.repositories.IDiagnosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DiagnosticService {

    @Autowired
    private IDiagnosticRepository diagnosticRepository;

    public ArrayList<DiagnosticModel> getDiagnostics(){
        return (ArrayList<DiagnosticModel>) this.diagnosticRepository.findAll();
    }
    public Optional<DiagnosticModel>  getDiagnosticById(Long id){
        return this.diagnosticRepository.findById(id);
    }
    public DiagnosticModel saveDiagnostic(DiagnosticModel diagnostic){
        return this.diagnosticRepository.save(diagnostic);
    }
    public DiagnosticModel updateDiagnosticById(DiagnosticModel request, Long id){
        DiagnosticModel diagnostic = this.diagnosticRepository.findById(id).get();
        diagnostic.setDiagnostic(request.getDiagnostic());
        diagnostic.setDate(request.getDate());
        diagnostic.setClient(request.getClient());
        diagnostic.setDeviceState(request.getDeviceState());
        diagnostic.setRepairCost(request.getRepairCost());
        diagnostic.setTechnician(request.getTechnician());
        diagnostic.setDeviceModel(request.getDeviceModel());
        return  this.diagnosticRepository.save(diagnostic);
    }
    public void deleteDiagnostic(Long id){
        try {
            this.diagnosticRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
