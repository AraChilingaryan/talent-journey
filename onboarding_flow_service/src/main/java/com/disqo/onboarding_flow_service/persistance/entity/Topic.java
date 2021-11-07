package com.disqo.onboarding_flow_service.persistance.entity;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Entity
@Table(name = "topic", schema = "onboarding_service_db")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

//    @OneToMany(cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<URL> resources;

    // TODO we can't have URL

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "topic")
    private List<Task> tasks;

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

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }
}
