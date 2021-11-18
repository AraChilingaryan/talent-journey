package com.disqo.talent_service.persistance;

import com.disqo.talent_service.persistance.entity.Talent;
import com.disqo.talent_service.service.dto.TalentResponseDTO;

import java.util.List;

public interface TalentCustomRepository {
    List<Talent> findBySpecializationId(Long specializationId);

}
