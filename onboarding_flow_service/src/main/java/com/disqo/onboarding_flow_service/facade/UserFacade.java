package com.disqo.onboarding_flow_service.facade;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;

import java.util.List;

public interface UserFacade {

    MenteeDto createMentee(MenteeDto menteeDto);

    MenteeDto findMenteeById(Long id);

    MentorDto findMentorById(Long id);

    List<MenteeDto> getAllMentees();

    List<MentorDto> getAllMentors();

    boolean deleteMentee(Long id);

    boolean deleteMentor(Long id);

}
