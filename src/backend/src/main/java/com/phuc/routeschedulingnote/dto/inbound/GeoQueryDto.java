package com.phuc.routeschedulingnote.dto.inbound;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoQueryDto implements Serializable {

    @JsonProperty("features")
    private List<GeoFeatureDto> features;

}
