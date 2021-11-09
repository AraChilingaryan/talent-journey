package com.disqo.interview_flow_service.converter;

import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.service.dto.TalentDTO;

import java.util.List;

public interface TalentConverter {
    List<TalentDTO> bulkConvertToDTO(List<Talent> talents);

    TalentDTO convertToDTO(Talent talent);

    List<Talent> bulkConvertToEntity(List<TalentDTO> talents);

    Talent convertToEntity(TalentDTO talentDTO);
}
