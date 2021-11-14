package com.disqo.authentication_service.service.impl;


import com.disqo.authentication_service.converter.PositionConverter;
import com.disqo.authentication_service.converter.UserConverter;
import com.disqo.authentication_service.persistance.model.Role;
import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.repository.RoleRepository;
import com.disqo.authentication_service.repository.UserRepository;
import com.disqo.authentication_service.rest.dto.UserDTO;
import com.disqo.authentication_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
   // private final PositionConverter positionConverter;


    public UserServiceImpl(UserRepository userRepository,

                           RoleRepository roleRepository, UserConverter userConverter,
                           PasswordEncoder passwordEncoder
                  //         PositionConverter positionConverter
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
       // this.positionConverter = positionConverter;
    }

    @Override
    public User update(String username, UserDTO userDTO) {
        final User user = this.userRepository.findByUsername(username);
             //   .orElseThrow(() -> new RuntimeException("Not found item by this username"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
     //   user.setPosition(positionConverter.convertToEntity(userDTO.getPositionDTO()));
      //  user.setPassword(userDTO.getPassword());


        return user;
    }

    @Override
    public User register(UserDTO userDTO) {
//        LOGGER.debug("Requested to create user for email - {}", userDTO.getEmail());
//        final User user = this.userConverter.convert(userDTO);
//      //  final Set<Role> userRoles = new HashSet<>();
//      //  final Role role = roleRepository.findByName("ROLE_USER")
//       //        .orElseThrow(() -> new RuntimeException("Not found ROLE_USER"));
//      //  userRoles.add(role);
//       // user.setRoles(userRoles);
//        User registeredUser = this.userRepository.save(user);
//        LOGGER.debug("IN register user : {} user successfully registered", registeredUser);
//        return registeredUser;
      //  Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
     //   userDTO.addRole(userRole);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public User findByUsername(String username) {
        final User user = this.userRepository.findByUsername(username);
        //.orElseThrow(() -> new UsernameNotFoundException(String.format("Not found user by this username%s", username)));
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
