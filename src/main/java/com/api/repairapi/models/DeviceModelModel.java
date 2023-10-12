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
    private String model_image;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @Column
    private String problem;

    @Column
    private String type;
}
