package com.disqo.talent_service.persistance.entity;

import com.disqo.talent_service.persistance.enums.TalentStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "talent", schema = "talent_service_db")
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @Column(name = "email", unique = true)
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 9, max = 12)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TalentStatus talentStatus;

    @Column(name = "cv_file_name")
    private String cvFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public TalentStatus getTalentStatus() {
        return talentStatus;
    }

    public void setTalentStatus(TalentStatus talentStatus) {
        this.talentStatus = talentStatus;
    }

    public String getFullName(){
        return this.getName() + "_" + this.getSurname();
    }

    public String getCvFileName() {
        return cvFileName;
    }

    public void setCvFileName(String cvFileName) {
        this.cvFileName = cvFileName;
    }
}

