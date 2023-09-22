package com.api.repairapi.services;

import com.api.repairapi.models.SpareRequestModel;
import com.api.repairapi.repositories.ISpareRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SpareRequestService {

    @Autowired
    private ISpareRequestRepository spareRequestRepository;

    public ArrayList<SpareRequestModel> getSpareRequests(){
        return (ArrayList<SpareRequestModel>) this.spareRequestRepository.findAll();
    }

    public Optional<SpareRequestModel> getSpareRequestById(Long id){
        return this.spareRequestRepository.findById(id);
    }

    public SpareRequestModel saveSpareRequest(SpareRequestModel spareRequest){
        return this.spareRequestRepository.save(spareRequest);
    }

    public SpareRequestModel updateSpareRequestById(SpareRequestModel request, Long id){
        SpareRequestModel spareRequest = this.spareRequestRepository.findById(id).get();

        spareRequest.setDescription(request.getDescription());
        spareRequest.setDate(request.getDate());
        spareRequest.setTechnician(request.getTechnician());

        return this.spareRequestRepository.save(spareRequest);
    }

    public void deleteSpareRequestById(Long id){
        try {
            this.spareRequestRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
