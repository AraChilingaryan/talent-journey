package com.disqo.flow_manager_service.rest.dto;

import java.util.Objects;

public class OnBoardingRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long mentorId;
    private String displayName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnBoardingRequest that = (OnBoardingRequest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(mentorId, that.mentorId) && Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phoneNumber, mentorId, displayName);
    }

    @Override
    public String toString() {
        return "OnBoardingRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mentorId=" + mentorId +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
