package com.disqo.onboarding_flow_service.facade;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;

import java.util.List;

public interface MenteeFacade {

    MenteeDto create(MenteeDto menteeDto);

    MenteeDto findById(Long id);

    List<MenteeDto> getAll();

    boolean delete(Long id);


}
