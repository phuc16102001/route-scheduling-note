package com.phuc.routeschedulingnote.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phuc.routeschedulingnote.dto.CoordinatesDto;
import com.phuc.routeschedulingnote.dto.PlaceNoteDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ScheduleGetDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("createAt")
    private Instant createAt;

    @JsonProperty("placeNotes")
    private List<PlaceNoteDto> placeNotes;

    @JsonProperty("stops")
    private List<CoordinatesDto> stops;

}
