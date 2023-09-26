package com.api.repairapi.controllers;

import com.api.repairapi.models.DeviceModelModel;
import com.api.repairapi.services.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> deleteDeviceModel(@PathVariable Long id) {
        try {
            this.deviceModelService.deleteDeviceModel(id);
            return ResponseEntity.noContent().build(); // Eliminación exitosa, devuelve código 204 (No Content)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Error en el servidor, devuelve código 500 (Internal Server Error)
        }
    }
}

