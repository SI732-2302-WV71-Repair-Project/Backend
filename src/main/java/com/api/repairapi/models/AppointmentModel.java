package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date schedule_date;

    @Column
    private char state;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name ="device_id")
    private DeviceModel device;

    @Column
    private String description;

}
