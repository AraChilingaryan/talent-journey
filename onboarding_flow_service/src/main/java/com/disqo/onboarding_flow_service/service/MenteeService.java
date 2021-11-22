package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;

import java.util.List;

public interface MenteeService {

    List<Mentee> findALl();

    Mentee findById(Long id);

    Mentee create(MenteeDto menteeDTO, JiraUserDto jirUser);

    Mentee update(Long id, MenteeDto menteeDTO);

    boolean deleteById(Long id);
}
