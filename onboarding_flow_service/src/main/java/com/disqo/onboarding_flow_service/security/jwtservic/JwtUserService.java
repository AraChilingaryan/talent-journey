package com.disqo.onboarding_flow_service.security.jwtservic;

import com.disqo.onboarding_flow_service.security.model.AuthenticatedUser;
import com.disqo.onboarding_flow_service.security.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserService {

    private static final String ROLES_CLAIM_NAME = "roles";

    @Value("${jwt.secret}")
    private String secret;

    public AuthenticatedUser createUserFrom(final String token) {
        final UserModel parsedUser = parseToken(token);
        if (parsedUser == null) {
            throw new RuntimeException("JWT token is not valid");
        }

        final Collection<? extends GrantedAuthority> authorities =
                java.util.Arrays.stream(parseRolesAsString(parsedUser.getRoles()).split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new AuthenticatedUser(parsedUser.getUsername(), token, authorities);
    }

    @SuppressWarnings("unchecked")
    public UserModel parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            UserModel user = new UserModel();
            user.setUsername(body.getSubject());
            user.setRoles(body.get(ROLES_CLAIM_NAME, ArrayList.class));
            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    private String parseRolesAsString(final List<LinkedHashMap<String, String>> roles) {
        StringBuilder sb = new StringBuilder();
        final LinkedHashMap<String, String> rolesMap = roles.get(0);
        for (String roleAsString : rolesMap.values()) {
            sb.append(roleAsString);
        }
        return sb.toString();
    }
}

