package com.phuc.routeschedulingnote.dto.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.phuc.routeschedulingnote.dto.CoordinatesDto;
import com.phuc.routeschedulingnote.dto.PlaceNoteDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("placeNotes")
    private List<PlaceNoteDto> placeNotes;

    @JsonProperty("stops")
    private List<CoordinatesDto> stops;

}
