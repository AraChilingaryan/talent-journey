package com.disqo.interview_flow_service.service.impl;

import com.disqo.interview_flow_service.converter.TalentConverter;
import com.disqo.interview_flow_service.excaption.EmailNotFoundException;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.repositories.TalentRepository;
import com.disqo.interview_flow_service.service.TalentService;
import com.disqo.interview_flow_service.service.dto.TalentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {

    private final TalentRepository talentRepository;
    private final TalentConverter talentConverter;

    @Override
    @Transactional(readOnly = true)
    public Talent findById(Long id) {
        return this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findTalentByEmail(String email) {

        return this.talentRepository.findTalentByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("No talent found by this email", email));
    }

    @Override
    @Transactional
    public Talent saveTalent(TalentDTO talentDTO) {
        final Talent talent = talentConverter.convertToEntity(talentDTO);
        return this.talentRepository.save(talent);
    }

    @Override
    public boolean existById(Long id) {
        return talentRepository.existsById(id);
    }

}
