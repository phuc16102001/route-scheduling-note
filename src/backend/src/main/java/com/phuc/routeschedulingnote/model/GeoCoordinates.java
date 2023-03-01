package com.phuc.routeschedulingnote.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class GeoCoordinates {
    private double lat;
    private double lng;
}
