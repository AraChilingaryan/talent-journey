package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.excaption.EmailNotFoundException;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.service.dto.TalentDTO;

import java.util.List;

public interface TalentService {
    List<Talent> findALl();

    Talent findById(Long id) throws TalentNotFoundException;

    Talent findTalentByEmail(String email) throws EmailNotFoundException;


}
