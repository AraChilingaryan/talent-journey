package com.disqo.talent_service.service.dto;

import com.disqo.talent_service.service.enums.TalentStatusClientType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TalentResponseDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private SpecializationResponseDTO specialization;

    @JsonProperty(value = "status")
    private TalentStatusClientType talentStatusClientType;

    private String cvFileName;
}
