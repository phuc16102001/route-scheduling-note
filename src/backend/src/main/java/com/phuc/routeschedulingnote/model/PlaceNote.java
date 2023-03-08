package com.phuc.routeschedulingnote.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(PlaceScheduleKey.class)
public class PlaceNote {

    @Id
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Id
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private String note;
}
