package com.disqo.interview_flow_service.service.impl;

import com.disqo.interview_flow_service.excaption.UserNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.User;
import com.disqo.interview_flow_service.persistance.repositories.UserRepository;
import com.disqo.interview_flow_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found by this id", id));
    }
}

