package com.api.repairapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "breakdown_service")
@Data
public class BreakdownServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Date publishDate;

    @Column
    private Long totalScore;

    @Column
    private char type;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceModel device;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private CommentModel comment;

    @ManyToOne
    @JoinColumn(name="technician_id")
    private TechnicianModel technician;
}
