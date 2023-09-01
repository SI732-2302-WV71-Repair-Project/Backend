package com.api.repairapi.models;

import com.api.repairapi.repositories.IDeviceModelRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name ="device")
@Data
public class DeviceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name ="device_model_id")
    private DeviceModel deviceModel;

}
