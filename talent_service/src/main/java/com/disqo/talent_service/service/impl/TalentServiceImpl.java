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
import com.disqo.talent_service.service.dto.TalentResponseDTO;
import com.disqo.talent_service.service.enums.TalentStatusClientType;
import com.disqo.talent_service.service.utils.MailGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {

    private final TalentRepository talentRepository;
    private final SpecializationService specializationService;
    private final TalentConverter talentConverter;
    private final MailClient mailClient;
    private final AmazonClientService amazonClientService;

    @Override
    @Transactional(readOnly = true)
    public List<Talent> findALl() {
        log.info("In findAll Talent requested to get all talents");
        return this.talentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findById(Long id) {
        log.info("In findById Talent requested to get the talent with id {}", id);
        return this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
    }

    @Override
    @Transactional
    public Talent create(TalentRequestDTO talentDTO) {
        log.info("Requested to create a talent");
        final Talent talent = talentConverter.convertToEntity(talentDTO);
        talent.setTalentStatus(TalentStatus.APPLIED);
        mailClient.sendEmail(MailGenerator.mailGenerator(talent));
        log.info("In create Talent talent successfully created");
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public Talent update(Long id, TalentRequestDTO talentDTO) {
        log.info("Requested to update a talent with id {}", id);
        final Talent talent = this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
        talent.setName(talentDTO.getName());
        talent.setSurname(talentDTO.getSurname());
        talent.setEmail(talentDTO.getEmail());
        talent.setPhoneNumber(talentDTO.getPhoneNumber());
        talent.setSpecialization(specializationService.findById(talentDTO.getSpecializationId()));
        log.info("In update Talent talent with id {} successfully updated", id);
        return talentRepository.save(talent);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a talent with id {}", id);
        if (!talentRepository.existsById(id)) {
            throw new TalentNotFoundException("No talent found by this id", id);
        }
        talentRepository.deleteById(id);
        log.info("In deleteById Talent talent with id {} successfully deleted", id);
        return true;
    }

    @Override
    public TalentResponseDTO updateStatus(TalentRequestDTO talentRequestDTO) {
        String email = talentRequestDTO.getEmail();
        TalentStatusClientType status = talentRequestDTO.getTalentStatusClientType();
        log.info("Requested to update status to {} of a talent with email {}", status, email);
        final Talent talent = this.talentRepository.findByEmail(email)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this email", email));
        talent.setTalentStatus(TalentStatus.valueOf(status.name()));
        log.info("In updateStatus Talent the status of talent with email {} successfully updated to {}", email, status);
        final Talent savedTalent = talentRepository.save(talent);
        return talentConverter.convertToDTO(savedTalent);

    }

    @Override
    public List<Talent> findBySpecializationId(Long specializationId) {
        log.info("In findBySpecializationId Talent requested to get all talents with specialization id {}", specializationId);
        return talentRepository.findAllBySpecializationId(specializationId);
    }

    @Override
    public String addCVForTalent(Long id, MultipartFile file) throws IOException {
        final Talent talent = talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
        String cvFileName = amazonClientService.uploadFile(file, talent);
        talent.setCvFileName(cvFileName);
        talentRepository.save(talent);
        return talent.getCvFileName();
    }
}
