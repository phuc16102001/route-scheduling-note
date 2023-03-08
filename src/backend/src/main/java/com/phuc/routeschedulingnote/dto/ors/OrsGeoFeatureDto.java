package com.phuc.routeschedulingnote.dto.ors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrsGeoFeatureDto implements Serializable {

    @JsonProperty("geometry")
    private OrsSingleCoordinateDto geometry;

}
