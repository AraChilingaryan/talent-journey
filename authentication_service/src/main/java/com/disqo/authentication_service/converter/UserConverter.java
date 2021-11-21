package com.disqo.authentication_service.converter;


import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.rest.dto.UserDTO;

import java.util.List;

public interface UserConverter {

    User convert(UserDTO userDTO);

    UserDTO convert(User user);

    List<UserDTO> bulkConvert(List<User> users);
}
