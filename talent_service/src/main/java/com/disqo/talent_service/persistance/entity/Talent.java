package com.disqo.talent_service.persistance.entity;

import com.disqo.talent_service.persistance.enums.TalentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
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
}

