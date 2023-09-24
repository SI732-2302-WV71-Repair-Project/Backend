package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String cellphoneNumber;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Date birthDate;

    @Column
    private Character gender;

    @Column
    private Date lastConnection;

    @Column
    private String address;

    @Column
    private String district;

}
