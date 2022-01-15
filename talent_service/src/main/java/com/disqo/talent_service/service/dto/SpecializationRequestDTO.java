package com.disqo.talent_service.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecializationRequestDTO {
    @JsonProperty(value = "specialization")
    private String specializationClientType;
}
