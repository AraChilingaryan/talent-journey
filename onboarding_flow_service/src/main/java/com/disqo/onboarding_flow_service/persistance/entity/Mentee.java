package com.disqo.onboarding_flow_service.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mentee" ,  schema = "onboarding_service_db")
@Data
@NoArgsConstructor
public class Mentee extends User{
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Roadmap roadmap;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}
