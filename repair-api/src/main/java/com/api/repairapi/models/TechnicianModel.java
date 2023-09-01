package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "technician")
@Data
public class TechnicianModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

}
