package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.excaption.EmailNotFoundException;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.service.dto.TalentDTO;

public interface TalentService {

    Talent findById(Long id) throws TalentNotFoundException;

    Talent findTalentByEmail(String email) throws EmailNotFoundException;

    Talent saveTalent(TalentDTO talentDTO);
}
