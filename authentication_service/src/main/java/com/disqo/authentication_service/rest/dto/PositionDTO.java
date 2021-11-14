package com.disqo.authentication_service.rest.dto;


import com.disqo.authentication_service.persistance.enums.PositionType;
import com.disqo.authentication_service.persistance.model.Position;
import com.disqo.authentication_service.persistance.model.User;

import java.util.Objects;
import java.util.Set;

public class PositionDTO {

    private Long id;
    private PositionType positionType;
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "PositionDTO{" +
                "id=" + id +
                ", positionType=" + positionType +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDTO that = (PositionDTO) o;
        return Objects.equals(id, that.id) && positionType == that.positionType && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionType, users);
    }
}
