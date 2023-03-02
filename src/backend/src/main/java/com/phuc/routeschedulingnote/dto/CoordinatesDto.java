package com.phuc.routeschedulingnote.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinatesDto implements Serializable {

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lng")
    private double lng;
}
