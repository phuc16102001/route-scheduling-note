package com.phuc.routeschedulingnote.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="place")
@Data
public class Place {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private Coordinates coordinates;

    @JsonIgnore
    @OneToMany(mappedBy = "place")
    private List<PlaceNote> placeNotes;

    @ManyToOne
    private User user;

}
