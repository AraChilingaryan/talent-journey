package com.disqo.authentication_service.service.auth.impl;

import com.disqo.authentication_service.config.security.JWTTokenProvider;
import com.disqo.authentication_service.rest.auth.model.AuthRequest;
import com.disqo.authentication_service.service.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JWTTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String login(final AuthRequest loginRequest) {
        LOGGER.info("Login procces");
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
           SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.createToken(authentication);

        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    }




