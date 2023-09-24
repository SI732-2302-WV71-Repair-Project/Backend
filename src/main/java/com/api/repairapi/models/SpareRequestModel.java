package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table (name = "spare_request")
@Data
public class SpareRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Date date;

    @JoinColumn(name = "technician_id")
    @ManyToOne
    private TechnicianModel technician;

    @JoinColumn(name = "appointment_id")
    @ManyToOne
    private AppointmentModel appointment;
}
