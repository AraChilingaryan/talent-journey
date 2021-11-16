package com.disqo.onboarding_flow_service.security.jwtservic;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final static Logger log = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    private final JwtTokenService jwtService;

    public JwtAuthenticationProvider(final JwtTokenService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            String token = (String) authentication.getCredentials();
            String username = jwtService.getUsernameFromToken(token);

            return jwtService.validateToken(token)
                    .map(aBoolean -> new JwtAuthenticatedProfile(username))
                    .orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));

        } catch (JwtException ex) {
            log.error(String.format("Invalid JWT Token: %s", ex.getMessage()));
            throw new JwtAuthenticationException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
