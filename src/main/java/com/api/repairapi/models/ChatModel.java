package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "chat")
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private TechnicianModel technician;

    @Column
    private String message;

    @Column
    private Date date;

}
