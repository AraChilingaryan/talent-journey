package com.disqo.authentication_service.persistance.model;

import com.disqo.authentication_service.persistance.enums.RoleName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", schema = "user_db" )
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users ;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleType() {
        return name;
    }

    public void setRoleType(RoleName roleName) {
        this.name = roleName;
    }
}
