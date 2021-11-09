package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.persistance.entity.talent.Specialization;
import com.disqo.interview_flow_service.service.dto.SpecializationDTO;

import java.util.List;

public interface SpecializationService {

    Specialization findById(Long id);

}
