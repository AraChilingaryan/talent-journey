package com.disqo.flow_manager_service.rest.dto;

import java.util.List;
import java.util.Objects;

public class InterviewRequest {

    private String calendarURI;
    private List<Long> userIDs;
    private TalentDTO talentDTO;
    private Long specializationId;

    public String getCalendarURI() {
        return calendarURI;
    }

    public void setCalendarURI(String calendarURI) {
        this.calendarURI = calendarURI;
    }

    public List<Long> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Long> userIDs) {
        this.userIDs = userIDs;
    }

    public TalentDTO getTalentDTO() {
        return talentDTO;
    }

    public void setTalentDTO(TalentDTO talentDTO) {
        this.talentDTO = talentDTO;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewRequest that = (InterviewRequest) o;
        return Objects.equals(calendarURI, that.calendarURI) && Objects.equals(userIDs, that.userIDs) && Objects.equals(talentDTO, that.talentDTO) && Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarURI, userIDs, talentDTO, specializationId);
    }

    @Override
    public String toString() {
        return "InterviewRequest{" +
                "calendarURI='" + calendarURI + '\'' +
                ", userIDs=" + userIDs +
                ", talentDTO=" + talentDTO +
                ", specializationId=" + specializationId +
                '}';
    }
}
