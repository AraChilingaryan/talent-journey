package com.disqo.talent_service.converter.impl;

import com.disqo.talent_service.converter.SpecializationConverter;
import com.disqo.talent_service.persistance.entity.Specialization;
import com.disqo.talent_service.service.dto.SpecializationRequestDTO;
import com.disqo.talent_service.service.dto.SpecializationResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecializationConverterImpl implements SpecializationConverter {
    @Override
    public List<SpecializationResponseDTO> bulkConvertToDTO(List<Specialization> specializations) {
        return specializations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpecializationResponseDTO convertToDTO(Specialization specialization) {
        final SpecializationResponseDTO specializationResponseDTO = new SpecializationResponseDTO();
        specializationResponseDTO.setId(specialization.getId());
        specializationResponseDTO.setSpecializationClientType(specialization.getSpecializationType());
        return specializationResponseDTO;
    }

    @Override
    public List<Specialization> bulkConvertToEntity(List<SpecializationRequestDTO> specializationRequestDTOList) {
        return specializationRequestDTOList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Specialization convertToEntity(SpecializationRequestDTO specializationRequestDTO) {
        final Specialization specialization = new Specialization();
        specialization.setSpecializationType(specializationRequestDTO.getSpecializationClientType());
        return specialization;
    }
}
