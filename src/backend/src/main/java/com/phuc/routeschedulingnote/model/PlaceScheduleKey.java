package com.phuc.routeschedulingnote.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PlaceScheduleKey implements Serializable {

    private Integer place;
    private Integer schedule;

}
