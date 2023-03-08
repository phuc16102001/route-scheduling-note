package com.phuc.routeschedulingnote.dto.ors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrsSingleCoordinateDto implements Serializable {

    @JsonProperty("coordinates")
    private List<Double> coordinates;

}
