package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "diagnostic")
public class DiagnosticModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String diagnostic;

    @Column
    private String deviceState;

    @Column
    private Double repairCost;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private TechnicianModel technician;

    @ManyToOne
    @JoinColumn(name = "device_model_id")
    private DeviceModelModel deviceModel;

}
