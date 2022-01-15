package com.disqo.talent_service.converter;

import com.disqo.talent_service.persistance.entity.Specialization;
import com.disqo.talent_service.service.dto.SpecializationRequestDTO;
import com.disqo.talent_service.service.dto.SpecializationResponseDTO;

import java.util.List;

public interface SpecializationConverter {

    List<SpecializationResponseDTO> bulkConvertToDTO(List<Specialization> specializations);

    SpecializationResponseDTO convertToDTO(Specialization specialization);

    Specialization convertToEntity(SpecializationRequestDTO specializationRequestDTO);

}
