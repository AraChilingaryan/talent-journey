package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.exception.MenteeNotFoundException;
import com.disqo.onboarding_flow_service.persistance.MenteeRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenteeServiceImpl implements MenteeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenteeServiceImpl.class);

    private final MenteeRepository menteeRepository;
    private final MenteeConverter menteeConverter;
    private final MentorService mentorService;

    public MenteeServiceImpl(MenteeRepository menteeRepository, MenteeConverter menteeConverter,
                             MentorService mentorService) {
        this.menteeRepository = menteeRepository;
        this.menteeConverter = menteeConverter;
        this.mentorService = mentorService;
    }

    @Override
    public List<Mentee> findALl() {
        LOGGER.info("In findAll Mentee requested to get all mentees");
        return this.menteeRepository.findAll();
    }

    @Override
    public Mentee findById(Long id) {
        LOGGER.info("In findById Mentee requested to get the mentee with id {}", id);
        return this.menteeRepository.findById(id)
                .orElseThrow(() -> new MenteeNotFoundException("No mentee found by this id", id));
    }

    @Override
    public Mentee create(MenteeDTO menteeDTO) {
        final Mentee mentee = menteeConverter.convertToEntity(menteeDTO);
        return menteeRepository.save(mentee);
    }

    @Override
    public Mentee update(Long id, MenteeDTO menteeDTO) {
        final Mentee mentee = menteeRepository.findById(id)
                .orElseThrow(() -> new MenteeNotFoundException("No mentee found by this id", id));
        mentee.setFirstName(menteeDTO.getFirstName());
        mentee.setLastName(menteeDTO.getLastName());
        mentee.setEmail(menteeDTO.getEmail());
        mentee.setPhoneNumber(menteeDTO.getPhoneNumber());
        mentee.setDisplayName(menteeDTO.getDisplayName());
        mentee.setAccountId(menteeDTO.getAccountId());
        mentee.setMentor(mentorService.findById(menteeDTO.getMentorId()));
        return menteeRepository.save(mentee);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a mentee with id {}", id);
        if (!menteeRepository.existsById(id)) {
            throw new MenteeNotFoundException("No mentee found by this id", id);
        }
        menteeRepository.deleteById(id);
        LOGGER.info("In deleteById Mentee mentee with id {} successfully deleted", id);
        return true;
    }
}
