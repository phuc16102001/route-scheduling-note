package com.phuc.routeschedulingnote.dto.ors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrsDirectionQueryDto implements Serializable {

    @JsonProperty("coordinates")
    private List<List<Double>> coordinates;

}
