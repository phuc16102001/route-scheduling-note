package com.phuc.routeschedulingnote.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ScheduleListDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("createAt")
    private Instant createAt;

}
