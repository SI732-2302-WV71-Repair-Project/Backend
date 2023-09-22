package com.api.repairapi.controllers;

import com.api.repairapi.models.BreakdownServiceModel;
import com.api.repairapi.services.BreakdownServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/breakdown-service")
public class BreakdownServiceController {

    @Autowired
    private BreakdownServiceService breakdownServiceService;

    @GetMapping
    public ArrayList<BreakdownServiceModel> getBreakdownServices(){
        return this.breakdownServiceService.getBreakdownServices();
    }

    @GetMapping(path = "/{id}")
    public Optional<BreakdownServiceModel> getBreakdownServiceByID(@PathVariable Long id){
        return this.breakdownServiceService.getBreakdownServiceById(id);
    }

    @PostMapping
    public BreakdownServiceModel saveBreakdownService(@RequestBody BreakdownServiceModel breakdownService){
        return this.breakdownServiceService.saveBreakdownService(breakdownService);
    }
    
    @PutMapping(path = "/{id}")
    public BreakdownServiceModel updateBreakdownService(@RequestBody BreakdownServiceModel request, @PathVariable Long id){
        return this.breakdownServiceService.updateBreakdownService(request, id);
    }

    @DeleteMapping(path = "/{id}")
    private void deleteBreakdownService(@PathVariable Long id){
        this.breakdownServiceService.deleteBreakdownService(id);
    }
}
