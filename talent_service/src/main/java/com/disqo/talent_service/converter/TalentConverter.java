package com.disqo.talent_service.converter;

import com.disqo.talent_service.persistance.entity.Talent;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import com.disqo.talent_service.service.dto.TalentResponseDTO;

import java.util.List;

public interface TalentConverter {

    List<TalentResponseDTO> bulkConvertToDTO(List<Talent> talents);

    TalentResponseDTO convertToDTO(Talent talent);

    List<Talent> bulkConvertToEntity(List<TalentRequestDTO> talents);

    Talent convertToEntity(TalentRequestDTO talentRequestDTO);

}
