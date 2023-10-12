package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
public class PaymentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name="device_model_id")
    private DeviceModelModel deviceModel;

    @Column
    private Double amount;

    @Column
    private String typeService;

    @Column
    private String paymentStatus;

    @Column
    private String paymentMethod;

}
