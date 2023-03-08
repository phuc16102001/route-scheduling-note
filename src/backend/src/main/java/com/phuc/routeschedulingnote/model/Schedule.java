package com.phuc.routeschedulingnote.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "schedule")
    private List<PlaceNote> placeNotes;

    @OneToMany(mappedBy = "schedule")
    private List<Stop> stops;

}
