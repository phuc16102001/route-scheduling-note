package com.phuc.routeschedulingnote.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("coordinates")
    private CoordinatesDto coordinates;
}
