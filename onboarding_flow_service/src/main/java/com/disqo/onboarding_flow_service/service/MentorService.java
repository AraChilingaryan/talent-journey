package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;

import java.util.List;

public interface MentorService {

    List<Mentor> findALl();

    Mentor findById(Long id);

    Mentor create(MentorDto mentorDTO);

    Mentor update(Long id, MentorDto mentorDTO);

    boolean deleteById(Long id);

}
