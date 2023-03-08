package com.phuc.routeschedulingnote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phuc.routeschedulingnote.model.Place;
import lombok.Data;

@Data
public class PlaceNoteDto {

    @JsonProperty("place")
    private Place place;

    @JsonProperty("note")
    private String note;
}
