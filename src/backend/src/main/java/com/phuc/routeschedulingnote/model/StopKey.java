package com.phuc.routeschedulingnote.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StopKey implements Serializable {

    private Integer schedule;
    private Integer stopOrder;

}
