package com.phuc.routeschedulingnote.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleGetDto {

    @JsonProperty("name")
    String name;
}
