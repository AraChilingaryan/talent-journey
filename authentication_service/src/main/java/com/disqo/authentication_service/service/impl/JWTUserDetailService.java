package com.disqo.authentication_service.service.impl;


import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.service.UserService;
import com.disqo.authentication_service.service.user.model.JWTUserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUserDetailService.class);

    private final UserService userService;

    public JWTUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.userService.findByUsername(username);
        LOGGER.debug("IN loadUserByUsername  - user: with username: {} successfully loaded by ", username);
        return JWTUserFactory.create(user);
    }
}
