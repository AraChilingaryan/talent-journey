package com.disqo.onboarding_flow_service.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mentee", schema = "onboarding_service_db")
@Data
@NoArgsConstructor
public class Mentee extends User {
    @OneToOne(mappedBy = "mentee", cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Roadmap roadmap;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}
