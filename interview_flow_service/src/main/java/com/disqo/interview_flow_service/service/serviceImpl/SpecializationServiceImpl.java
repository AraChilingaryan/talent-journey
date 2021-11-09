package com.disqo.interview_flow_service.service.serviceImpl;

import com.disqo.interview_flow_service.converter.SpecializationConverter;
import com.disqo.interview_flow_service.excaption.SpecializationNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.talent.Specialization;
import com.disqo.interview_flow_service.persistance.repositories.SpecializationRepository;
import com.disqo.interview_flow_service.service.SpecializationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SpecializationServiceImpl implements SpecializationService {
    private final SpecializationRepository specializationRepository;
    private final SpecializationConverter specializationConverter;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository, SpecializationConverter specializationConverter) {
        this.specializationRepository = specializationRepository;
        this.specializationConverter = specializationConverter;
    }


    @Override
    @Transactional(readOnly = true)
    public Specialization findById(Long id) {
        return this.specializationRepository.findById(id)
                .orElseThrow(() -> new SpecializationNotFoundException("No specialization found with this id",id));
    }


}
