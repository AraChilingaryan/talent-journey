package com.disqo.flow_manager_service.converter.impl;

import com.disqo.flow_manager_service.rest.dto.InterviewClientRequest;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.converter.Convert;
import com.disqo.flow_manager_service.rest.dto.UserServiceRequest;
import org.springframework.stereotype.Component;



@Component
public class ConvertImpl implements Convert {


    public UserServiceRequest convertToUserDTO(InterviewClientRequest interviewClientRequest) {

        UserServiceRequest userServiceRequest = new UserServiceRequest();
        userServiceRequest.setFirstName(interviewClientRequest.getName());
        userServiceRequest.setLastName(interviewClientRequest.getSurname());
        userServiceRequest.setEmail(interviewClientRequest.getEmail());
        userServiceRequest.setPhoneNumber(interviewClientRequest.getPhoneNumber());
        userServiceRequest.setUsername(interviewClientRequest.getEmail());
        userServiceRequest.setPassword(interviewClientRequest.getPhoneNumber());


        return userServiceRequest;
    }

    public TalentDTO convertToTalentDTO(InterviewClientRequest interviewClientRequest){
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setName(interviewClientRequest.getName());
        talentDTO.setSurname(interviewClientRequest.getSurname());
        talentDTO.setPhoneNumber(interviewClientRequest.getPhoneNumber());
        talentDTO.setEmail(interviewClientRequest.getEmail());
        talentDTO.setStatus("APPLIED");

        return talentDTO;
    }


}
