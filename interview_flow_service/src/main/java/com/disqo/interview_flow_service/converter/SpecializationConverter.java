package com.disqo.interview_flow_service.converter;

import com.disqo.interview_flow_service.persistance.entity.talent.Specialization;
import com.disqo.interview_flow_service.service.dto.SpecializationDTO;

import java.util.List;

public interface SpecializationConverter {
    List<SpecializationDTO> bulkConvertToDTO(List<Specialization> specializations);

    SpecializationDTO convertToDTO(Specialization specialization);

    List<Specialization> bulkConvertToEntity(List<SpecializationDTO> specializationDTOList);

    Specialization convertToEntity(SpecializationDTO specializationDTO);
}
