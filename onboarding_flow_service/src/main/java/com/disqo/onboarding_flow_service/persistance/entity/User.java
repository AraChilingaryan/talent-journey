package com.disqo.onboarding_flow_service.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

@MappedSuperclass
@Data
@NoArgsConstructor
public class User extends AbstractAuditAwareBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "jira_display_name")
    private String displayName;

    @Column(name = "jira_account_id")
    private String accountId;

    @Column(name = "self")
    private String self;


//    private PositionType position;
}
