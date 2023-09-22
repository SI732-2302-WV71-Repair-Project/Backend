package com.api.repairapi.controllers;

import com.api.repairapi.models.SpareRequestModel;
import com.api.repairapi.services.SpareRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/spare-request")
public class SpareRequestController {

    @Autowired
    private SpareRequestService spareRequestService;

    @GetMapping
    public ArrayList<SpareRequestModel> getSpareRequests(){
        return this.spareRequestService.getSpareRequests();
    }

    @GetMapping(path = "/{id}")
    public Optional<SpareRequestModel>  getSpareRequestById(@PathVariable Long id){
        return this.spareRequestService.getSpareRequestById(id);
    }

    @PostMapping
    public SpareRequestModel saveSpareRequest(@RequestBody SpareRequestModel spareRequest){
        return this.spareRequestService.saveSpareRequest(spareRequest);
    }

    @PutMapping(path = "/{id}")
    public SpareRequestModel updateSpareRequestById(@RequestBody SpareRequestModel request, @PathVariable Long id){
        return this.spareRequestService.updateSpareRequestById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSpareRequestById(@PathVariable Long id){
        this.spareRequestService.deleteSpareRequestById(id);
    }
}

