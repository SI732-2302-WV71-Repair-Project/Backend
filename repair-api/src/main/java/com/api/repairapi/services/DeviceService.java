package com.api.repairapi.services;

import com.api.repairapi.models.DeviceModel;
import com.api.repairapi.repositories.IDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private IDeviceRepository deviceRepository;

    public ArrayList<DeviceModel> getDevices(){
        return (ArrayList<DeviceModel>) this.deviceRepository.findAll();
    }

    public Optional<DeviceModel> getDeviceById(Long id){
        return this.deviceRepository.findById(id);
    }

    public DeviceModel saveDevice(DeviceModel device){
        return this.deviceRepository.save(device);
    }

    public DeviceModel updateDevice(DeviceModel request, Long id){
        DeviceModel device = this.deviceRepository.findById(id).get();

        device.setName(request.getName());
        device.setDeviceModel(request.getDeviceModel());

        return this.deviceRepository.save(device);
    }

    public void deleteDevice(Long id){
        try{
            this.deviceRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
