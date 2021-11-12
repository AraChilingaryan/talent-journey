package com.disqo.talent_service.service.impl;

import com.disqo.talent_service.client.MailClient;
import com.disqo.talent_service.converter.TalentConverter;
import com.disqo.talent_service.exception.TalentNotFoundException;
import com.disqo.talent_service.persistance.TalentRepository;
import com.disqo.talent_service.persistance.entity.Talent;
import com.disqo.talent_service.persistance.enums.TalentStatus;
import com.disqo.talent_service.service.AmazonClientService;
import com.disqo.talent_service.service.SpecializationService;
import com.disqo.talent_service.service.TalentService;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class TalentServiceImpl implements TalentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TalentServiceImpl.class);

    private final TalentRepository talentRepository;
    private final SpecializationService specializationService;
    private final TalentConverter talentConverter;
    private final MailClient mailClient;
    private final AmazonClientService amazonClientService;

    public TalentServiceImpl(TalentRepository talentRepository, SpecializationService specializationService,
                             TalentConverter talentConverter, MailClient mailClient, AmazonClientService amazonClientService) {
        this.talentRepository = talentRepository;
        this.specializationService = specializationService;
        this.talentConverter = talentConverter;
        this.mailClient = mailClient;
        this.amazonClientService = amazonClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Talent> findALl() {
        LOGGER.info("In findAll Talent requested to get all talents");
        return this.talentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findById(Long id) {
        LOGGER.info("In findById Talent requested to get the talent with id {}", id);
        return this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
    }

    @Override
    @Transactional
    public Talent create(TalentRequestDTO talentDTO) {
        LOGGER.info("Requested to create a talent");
        final Talent talent = talentConverter.convertToEntity(talentDTO);
        talent.setTalentStatus(TalentStatus.APPLIED);
        // TODO mailClient.sendEmail();
        LOGGER.info("In create Talent talent successfully created");
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public Talent update(Long id, TalentRequestDTO talentDTO) {
        LOGGER.info("Requested to update a talent with id {}", id);
        final Talent talent = this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
        talent.setName(talentDTO.getName());
        talent.setSurname(talentDTO.getSurname());
        talent.setEmail(talentDTO.getEmail());
        talent.setPhoneNumber(talentDTO.getPhoneNumber());
        talent.setSpecialization(specializationService.findById(talentDTO.getSpecializationId()));
        LOGGER.info("In update Talent talent with id {} successfully updated", id);
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a talent with id {}", id);
        if (!talentRepository.existsById(id)) {
            throw new TalentNotFoundException("No talent found by this id", id);
        }
        talentRepository.deleteById(id);
        LOGGER.info("In deleteById Talent talent with id {} successfully deleted", id);
        return true;
    }

    @Override
    public Talent updateStatus(Long id, String status) {
        LOGGER.info("Requested to update status to {} of a talent with id {}", status, id);
        final Talent talent = this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
        talent.setTalentStatus(TalentStatus.valueOf(status.toUpperCase(Locale.ROOT)));
        LOGGER.info("In updateStatus Talent the status of talent with id {} successfully updated to {}", id, status);
        return talentRepository.save(talent);
    }

    @Override
    public List<Talent> findBySpecializationId(Long specializationId) {
        LOGGER.info("In findBySpecializationId Talent requested to get all talents with specialization id {}", specializationId);
        return talentRepository.findBySpecializationId(specializationId);
    }
}
