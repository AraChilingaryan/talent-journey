package com.disqo.talent_service.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SpecializationResponseDTO {

    private Long id;
    @JsonProperty(value = "specialization")
    private String specializationClientType;
    @JsonIgnore
    private List<TalentRequestDTO> talentDTOList;
}
