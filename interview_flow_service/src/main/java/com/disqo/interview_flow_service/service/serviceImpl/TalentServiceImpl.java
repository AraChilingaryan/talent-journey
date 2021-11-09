package com.disqo.interview_flow_service.service.serviceImpl;

import com.disqo.interview_flow_service.converter.TalentConverter;
import com.disqo.interview_flow_service.excaption.EmailNotFoundException;
import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.persistance.repositories.TalentRepository;
import com.disqo.interview_flow_service.service.TalentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TalentServiceImpl implements TalentService {

    private final TalentRepository talentRepository;
    private final TalentConverter talentConverter;

    public TalentServiceImpl(TalentRepository talentRepository, TalentConverter talentConverter) {
        this.talentRepository = talentRepository;
        this.talentConverter = talentConverter;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Talent> findALl() {
        return this.talentRepository.findAll();
    }

    @Override
    public Talent findById(Long id) {
        return this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id",id));
    }

    @Override
    public Talent findTalentByEmail(String email) {

        return this.talentRepository.findTalentByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("No talent found by this email",email));
    }

}
