package com.phuc.routeschedulingnote.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phuc.routeschedulingnote.dto.PlaceNoteDto;
import lombok.Data;

import java.util.List;

@Data
public class SchedulePostDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("placeNotes")
    private List<PlaceNoteDto> placeNotes;

}
