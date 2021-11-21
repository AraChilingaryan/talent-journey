package com.disqo.authentication_service.service.user.model;

import com.disqo.authentication_service.persistance.enums.Status;
import com.disqo.authentication_service.persistance.model.Role;
import com.disqo.authentication_service.persistance.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JWTUserFactory {

    public static JWTUser create(User user) {
        return new JWTUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrandAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrandAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(o -> new SimpleGrantedAuthority(o.getRoleType().name()))
                .collect(Collectors.toList());
    }
}
