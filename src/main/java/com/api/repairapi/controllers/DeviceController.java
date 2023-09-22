package com.api.repairapi.controllers;

import com.api.repairapi.models.DeviceModel;
import com.api.repairapi.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ArrayList<DeviceModel> getDevices(){
        return this.deviceService.getDevices();
    }

    @GetMapping(path = "/{id}")
    public Optional<DeviceModel>  getDeviceById(@PathVariable Long id){
        return this.deviceService.getDeviceById(id);
    }

    @PostMapping
    public DeviceModel saveDeviceModel(@RequestBody DeviceModel device){
        return this.deviceService.saveDevice(device);
    }

    @PutMapping(path = "/{id}")
    public DeviceModel updateDeviceMode(@RequestBody DeviceModel device, @PathVariable Long id){
        return this.deviceService.updateDevice(device, id);
    }

    @DeleteMapping(path ="/{id}")
    public void deleteDeviceModel(@PathVariable Long id){
        this.deviceService.deleteDevice(id);
    }
}
