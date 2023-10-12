package com.api.repairapi.services;

import com.api.repairapi.models.DeviceModelModel;
import com.api.repairapi.repositories.IDeviceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DeviceModelService {

    @Autowired
    private IDeviceModelRepository deviceModelRepository;

    public ArrayList<DeviceModelModel> getDeviceModels(){
        return (ArrayList<DeviceModelModel>) this.deviceModelRepository.findAll();
    }

    public Optional<DeviceModelModel> getDeviceModelById(Long id){
        return this.deviceModelRepository.findById(id);
    }

    public DeviceModelModel saveDeviceModel(DeviceModelModel deviceModel){
        return this.deviceModelRepository.save(deviceModel);
    }

    public DeviceModelModel updateDeviceModel(DeviceModelModel request, Long id){
        DeviceModelModel deviceModelModel = this.deviceModelRepository.findById(id).get();

        deviceModelModel.setBrand(request.getBrand());
        deviceModelModel.setModel(request.getModel());
        deviceModelModel.setModel_image(request.getModel_image());
        deviceModelModel.setClient(request.getClient());
        deviceModelModel.setProblem(request.getProblem());
        deviceModelModel.setType(request.getType());

        return this.deviceModelRepository.save(deviceModelModel);
    }

    public void deleteDeviceModel(Long id){
        try{
            this.deviceModelRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
