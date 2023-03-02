package com.phuc.routeschedulingnote.dto.inbound;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrsGeocodeDto implements Serializable {

    @JsonProperty("features")
    private List<OrsFeatureDto> features;

}
