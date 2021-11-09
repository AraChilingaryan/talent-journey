package com.disqo.interview_flow_service.service.dto;

import com.disqo.interview_flow_service.persistance.enums.TalentStatus;

import javax.validation.constraints.*;

public class TalentDTO {

    private Long id;

    @NotBlank(message = "name must not be blank")
    private java.lang.String name;

    @NotBlank(message = "surname must not be blank")
    private java.lang.String surname;

    @Email
    private java.lang.String email;

    @Pattern(regexp="\\d{9}")
    private java.lang.String phoneNumber;

//    @Positive
//    @Min(value = 50)
//    @Max(value = 100)
    private Integer overallScore;

    @NotEmpty
    private TalentStatus talentStatus;
//
//    @NotNull
//    private SpecializationDTO specialization;

//    private List<InterviewResponseDTO> interviewList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getSurname() {
        return surname;
    }

    public void setSurname(java.lang.String surname) {
        this.surname = surname;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public TalentStatus getTalentStatus() {
        return talentStatus;
    }

    public void setTalentStatus(TalentStatus talentStatus) {
        this.talentStatus = talentStatus;
    }
}
