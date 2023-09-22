package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "device_model")
@Data
public class DeviceModelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Long queryCost;
}
