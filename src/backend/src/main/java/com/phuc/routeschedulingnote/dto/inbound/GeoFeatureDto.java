package com.phuc.routeschedulingnote.dto.inbound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoFeatureDto implements Serializable {

    @JsonProperty("geometry")
    private GeoGeometryDto geometry;

}
