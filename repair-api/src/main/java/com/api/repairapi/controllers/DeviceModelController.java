package com.api.repairapi.controllers;

import com.api.repairapi.models.DeviceModelModel;
import com.api.repairapi.services.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/device-model")
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;

    @GetMapping
    public ArrayList<DeviceModelModel> getDeviceModels(){
        return this.deviceModelService.getDeviceModels();
    }

    @GetMapping(path = "/{id}")
    public Optional<DeviceModelModel>  getDeviceModelById(@PathVariable Long id)
    {
        return this.deviceModelService.getDeviceModelById(id);
    }

    @PostMapping
    public DeviceModelModel saveDeviceModel(@RequestBody DeviceModelModel deviceModel){
        return this.deviceModelService.saveDeviceModel(deviceModel);
    }

    @PutMapping(path = "/{id}")
    public DeviceModelModel updateDeviceModel(@RequestBody DeviceModelModel request, @PathVariable Long id){
        return this.deviceModelService.updateDeviceModel(request,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDeviceModel(@PathVariable Long id){
        try {
            this.deviceModelService.deleteDeviceModel(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

