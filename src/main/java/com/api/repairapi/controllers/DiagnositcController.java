package com.api.repairapi.controllers;

import com.api.repairapi.models.DiagnosticModel;
import com.api.repairapi.services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/diagnostic")
public class DiagnositcController {

    @Autowired
    private DiagnosticService diagnosticService;

    @GetMapping
    public ArrayList<DiagnosticModel> getDiagnostics(){
        return this.diagnosticService.getDiagnostics();
    }

    @GetMapping(path = "/{id}")
    public Optional<DiagnosticModel>  getDiagnosticById(@PathVariable Long id){
        return this.diagnosticService.getDiagnosticById(id);
    }

    @PostMapping
    public DiagnosticModel saveDiagnostic(@RequestBody DiagnosticModel diagnostic){
        return this.diagnosticService.saveDiagnostic(diagnostic);
    }

    @PutMapping(path = "/{id}")
    public DiagnosticModel updateDiagnostic(@RequestBody DiagnosticModel request, @PathVariable Long id){
        return this.diagnosticService.updateDiagnosticById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDiagnostic(@PathVariable Long id){
        this.diagnosticService.deleteDiagnostic(id);
    }

}
