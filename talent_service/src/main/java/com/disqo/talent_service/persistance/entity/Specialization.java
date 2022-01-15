package com.disqo.talent_service.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Data
@NoArgsConstructor
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
}
