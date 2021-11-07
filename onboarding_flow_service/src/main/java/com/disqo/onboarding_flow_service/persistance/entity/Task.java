package com.disqo.onboarding_flow_service.persistance.entity;

import com.disqo.onboarding_flow_service.persistance.enums.TaskStatus;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Task", schema = "onboarding_service_db")
public class Task extends AbstractAuditAwareBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;
//    private User reporter;
//    private User assignee;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus status;


  //  private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
