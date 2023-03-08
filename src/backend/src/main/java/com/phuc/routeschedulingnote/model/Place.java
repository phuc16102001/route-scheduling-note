package com.phuc.routeschedulingnote.model;


import jakarta.persistence.*;
import lombok.Data;

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

}
