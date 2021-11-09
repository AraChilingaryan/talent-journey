package com.disqo.interview_flow_service.service.serviceImpl;

import com.disqo.interview_flow_service.converter.UserConverter;
import com.disqo.interview_flow_service.excaption.UserNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.user.User;
import com.disqo.interview_flow_service.persistance.repositories.UserRepository;
import com.disqo.interview_flow_service.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found by this id", id));
    }
}

