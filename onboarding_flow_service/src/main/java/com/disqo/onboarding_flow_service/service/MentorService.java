package com.disqo.onboarding_flow_service.service;

import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.dto.MentorDTO;

import java.util.List;

public interface MentorService {

    List<Mentor> findALl();

    Mentor findById(Long id) throws RoadmapNotFoundException;

    Mentor create(MentorDTO mentorDTO);

    Mentor update(Long id, MentorDTO mentorDTO) throws RoadmapNotFoundException;

    boolean deleteById(Long id);

}
