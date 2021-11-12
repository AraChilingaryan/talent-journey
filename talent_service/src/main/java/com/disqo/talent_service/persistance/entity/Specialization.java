package com.disqo.talent_service.persistance.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "specialization", schema = "talent_service_db")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization_type", unique = true)
    @NotBlank(message = "Specialization type is mandatory")
    private String specializationType;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "specialization")
    private List<Talent> talentList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializationType() {
        return specializationType;
    }

    public void setSpecializationType(String specializationType) {
        this.specializationType = specializationType;
    }

    public List<Talent> getTalentList() {
        return talentList;
    }

    public void setTalentList(List<Talent> talentList) {
        this.talentList = talentList;
    }
}
