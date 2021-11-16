package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.MentorDTO;

import java.util.List;

public interface MenteeService {

    List<Mentee> findALl();

    Mentee findById(Long id);

    Mentee create(MenteeDTO menteeDTO);

    Mentee update(Long id, MenteeDTO menteeDTO);

    boolean deleteById(Long id);
}
