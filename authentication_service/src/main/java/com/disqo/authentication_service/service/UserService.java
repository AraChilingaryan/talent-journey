package com.disqo.authentication_service.service;


import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.rest.dto.UserDTO;

import java.util.List;

public interface UserService {

    User register(UserDTO userDTO);

    User update(String username, UserDTO userDTO);

    User findByUsername(String name);

    User findById(Long id);

    List<User> getAll();

    boolean delete(Long id);
}
