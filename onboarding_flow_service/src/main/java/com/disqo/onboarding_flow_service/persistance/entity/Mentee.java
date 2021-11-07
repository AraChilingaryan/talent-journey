package com.disqo.onboarding_flow_service.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name = "mentee" ,  schema = "onboarding_service_db")
public class Mentee extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Roadmap roadmap;

}
