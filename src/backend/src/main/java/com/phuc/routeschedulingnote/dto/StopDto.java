package com.phuc.routeschedulingnote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StopDto {

    @JsonProperty("stopOrder")
    private Integer stopOrder;

    @JsonProperty("coordinates")
    private CoordinatesDto coordinates;

}
