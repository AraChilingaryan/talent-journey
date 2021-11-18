package com.disqo.flow_manager_service.converter;

import com.disqo.flow_manager_service.rest.dto.InterviewClientRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;

public interface Convert {

    UserServiceRequest convertToUserDTO(InterviewClientRequest interviewClientRequest);

    TalentDTO convertToTalentDTO(InterviewClientRequest interviewClientRequest);
}
