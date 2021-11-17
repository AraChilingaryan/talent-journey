package com.disqo.talent_service.service.dto;

import com.disqo.talent_service.service.enums.TalentStatusClientType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TalentRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Long specializationId;
    @JsonProperty(value = "status")
    private TalentStatusClientType talentStatusClientType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

    public TalentStatusClientType getTalentStatusClientType() {
        return talentStatusClientType;
    }

    public void setTalentStatusClientType(TalentStatusClientType talentStatusClientType) {
        this.talentStatusClientType = talentStatusClientType;
    }
}
