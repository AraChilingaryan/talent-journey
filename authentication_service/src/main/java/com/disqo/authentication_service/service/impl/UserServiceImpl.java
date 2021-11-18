package com.disqo.authentication_service.service.impl;


import com.disqo.authentication_service.converter.UserConverter;
import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.repository.RoleRepository;
import com.disqo.authentication_service.repository.UserRepository;
import com.disqo.authentication_service.rest.dto.UserDTO;
import com.disqo.authentication_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserConverter userConverter,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User update(String username, UserDTO userDTO) {
        final User user = this.userRepository.findByUsername(username);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;
    }

    @Override
    public User register(UserDTO userDTO) {
        LOGGER.debug("Requested to create user for email - {}", userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public User findByUsername(String username) {
        final User user = this.userRepository.findByUsername(username);

        LOGGER.debug("IN findByUsername  - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(Long id) {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Not found user by this id%d", id)));
        LOGGER.debug("IN findById user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        final List<User> users = this.userRepository.findAll();
        LOGGER.debug("IN getAll  : {} user found", users.size());
        return users;
    }

    @Override
    public boolean delete(Long id) {
        this.userRepository.deleteById(id);
        LOGGER.debug("IN delete user deleted by id: {}", id);
        return this.userRepository.existsById(id);
    }
}
