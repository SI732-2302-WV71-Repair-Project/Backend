package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "comment")
@Data
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Date publishDate;

    @Column
    private Long rating;

    @ManyToOne
    @JoinColumn(name ="client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private TechnicianModel technician;


}
