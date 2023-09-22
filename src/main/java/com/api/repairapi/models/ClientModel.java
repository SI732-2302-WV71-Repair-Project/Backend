package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
