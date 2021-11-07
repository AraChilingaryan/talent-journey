package com.disqo.onboarding_flow_service.persistance.entity;


import com.sun.istack.NotNull;
import org.yaml.snakeyaml.comments.CommentType;

import javax.persistence.*;

@Entity
@Table(name = "comment", schema = "onboarding_service_db")
public class Comment extends AbstractAuditAwareBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

//    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//    private User reporter;
//
//    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//    private User assignee;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CommentType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public User getReporter() {
//        return reporter;
//    }
//
//    public void setReporter(User reporter) {
//        this.reporter = reporter;
//    }
//
//    public User getAssignee() {
//        return assignee;
//    }
//
//    public void setAssignee(User assignee) {
//        this.assignee = assignee;
//    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }
}
