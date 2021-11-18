package com.disqo.talent_service.service;

import com.disqo.talent_service.persistance.entity.Specialization;
import com.disqo.talent_service.service.dto.SpecializationRequestDTO;

import java.util.List;

public interface SpecializationService {

    List<Specialization> findALl();

    Specialization findById(Long id);

    Specialization create(SpecializationRequestDTO specializationDTO);

    Specialization update(Long id, SpecializationRequestDTO specializationDTO);

    boolean deleteById(Long id);

}
