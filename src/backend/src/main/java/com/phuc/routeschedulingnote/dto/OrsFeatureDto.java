package com.phuc.routeschedulingnote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrsFeatureDto implements Serializable {

    @JsonProperty("geometry")
    private OrsGeometryDto geometry;

}
