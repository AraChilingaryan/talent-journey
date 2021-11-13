package com.disqo.interview_flow_service.converter;

import com.disqo.interview_flow_service.persistance.entity.User;
import com.disqo.interview_flow_service.service.dto.UserDTO;

import java.util.List;

public interface UserConverter {
    List<UserDTO> bulkConvertToDTO(List<User> users);

    UserDTO convertToDTO(User user);

    List<User> bulkConvertToEntity(List<UserDTO> userDTOS);

    User convertToEntity(UserDTO userDTO);
}
