package com.phuc.routeschedulingnote.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="stop")
@IdClass(StopKey.class)
public class Stop {

    @Id
    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @Id
    private Integer stopOrder;
    private Coordinates coordinates;

}
