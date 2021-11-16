package com.disqo.onboarding_flow_service.persistance.entity;

import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "roadmap", schema = "onboarding_service_db")
@Data
@NoArgsConstructor
public class Roadmap extends AbstractAuditAwareBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "roadmap")
    private List<Sprint> sprints;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToOne
    @MapsId
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private RoadmapStatus status;

    @Column(name = "jira_project_key")
    private String jiraProjectKey;

    @Column(name = "name")
    private String name;
}
