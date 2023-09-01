package com.api.repairapi.services;

import com.api.repairapi.models.BreakdownServiceModel;
import com.api.repairapi.repositories.IBreakdownServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BreakdownServiceService {

    @Autowired
    private IBreakdownServiceRepository breakdownServiceRepository;

    public ArrayList<BreakdownServiceModel> getBreakdownServices(){
        return (ArrayList<BreakdownServiceModel>) this.breakdownServiceRepository.findAll();
    }

    public Optional<BreakdownServiceModel> getBreakdownServiceById(Long id){
        return this.breakdownServiceRepository.findById(id);
    }

    public BreakdownServiceModel saveBreakdownService(BreakdownServiceModel breakdownService){
        return this.breakdownServiceRepository.save(breakdownService);
    }

    public BreakdownServiceModel updateBreakdownService(BreakdownServiceModel request, Long id){
        BreakdownServiceModel breakdownService = this.breakdownServiceRepository.findById(id).get();

        breakdownService.setTitle(request.getTitle());
        breakdownService.setDescription(request.getDescription());
        breakdownService.setPublishDate(request.getPublishDate());
        breakdownService.setTotalScore(request.getTotalScore());
        breakdownService.setType(request.getType());
        breakdownService.setDevice(request.getDevice());
        breakdownService.setTechnician(request.getTechnician());
        breakdownService.setComment(request.getComment());

        return this.breakdownServiceRepository.save(breakdownService);
    }

    public void deleteBreakdownService(Long id){
        try {
            this.breakdownServiceRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
