package com.phuc.routeschedulingnote.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="schedule_stop")
public class ScheduleStop {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @Id
    private Integer stopOrder;

    private Coordinates coordinates;

}
