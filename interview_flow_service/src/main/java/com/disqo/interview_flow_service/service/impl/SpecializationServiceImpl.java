package com.disqo.interview_flow_service.service.impl;

import com.disqo.interview_flow_service.excaption.SpecializationNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.Specialization;
import com.disqo.interview_flow_service.persistance.repositories.SpecializationRepository;
import com.disqo.interview_flow_service.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {
    private final SpecializationRepository specializationRepository;

    @Override
    @Transactional(readOnly = true)
    public Specialization findById(Long id) {
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new SpecializationNotFoundException("No specialization found with this id",id));
    }


}
