package com.disqo.authentication_service.persistance.model;

import com.disqo.authentication_service.persistance.enums.PositionType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "position",  schema = "user_db")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_type")
    private PositionType positionType;
//
//    @ManyToMany(mappedBy = "position")
//    private Set<User> users;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
