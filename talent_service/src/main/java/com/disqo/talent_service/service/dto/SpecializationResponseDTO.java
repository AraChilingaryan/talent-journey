package com.disqo.talent_service.service.dto;

import java.util.List;

public class SpecializationResponseDTO {

    private Long id;
    private String specializationClientType;
    private List<TalentRequestDTO> talentDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializationClientType() {
        return specializationClientType;
    }

    public void setSpecializationClientType(String specializationClientType) {
        this.specializationClientType = specializationClientType;
    }

    public List<TalentRequestDTO> getTalentDTOList() {
        return talentDTOList;
    }

    public void setTalentDTOList(List<TalentRequestDTO> talentDTOList) {
        this.talentDTOList = talentDTOList;
    }
}
