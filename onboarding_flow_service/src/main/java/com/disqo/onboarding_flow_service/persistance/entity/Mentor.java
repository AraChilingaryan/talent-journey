package com.disqo.onboarding_flow_service.persistance.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentor", schema = "onboarding_service_db")
@Data
@NoArgsConstructor
public class Mentor extends User {

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "mentor")
    private List<Roadmap> roadmaps;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "mentor")
    private List<Mentee> mentees;
}
