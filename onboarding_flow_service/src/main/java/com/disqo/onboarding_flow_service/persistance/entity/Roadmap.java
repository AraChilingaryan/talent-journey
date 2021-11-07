package com.disqo.onboarding_flow_service.persistance.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "roadmap", schema = "onboarding_service_db")
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
    private List<Phase> phases;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToOne(targetEntity = Mentee.class, cascade = CascadeType.ALL)
    private Mentee mentee;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(final List<Phase> phases) {
        this.phases = phases;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(final Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public void setMentee(final Mentee mentee) {
        this.mentee = mentee;
    }
}
