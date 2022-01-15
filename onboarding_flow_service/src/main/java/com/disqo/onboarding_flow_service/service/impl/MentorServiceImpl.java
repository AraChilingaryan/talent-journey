package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.converter.MentorConverter;
import com.disqo.onboarding_flow_service.exception.MentorNotFoundException;
import com.disqo.onboarding_flow_service.persistance.MentorRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final MentorConverter mentorConverter;

    @Override
    public List<Mentor> findALl() {
        log.info("In findAll Mentor requested to get all mentros");
        return this.mentorRepository.findAll();
    }

    @Override
    public Mentor findById(Long id) {
        log.info("In findById Mentor requested to get the mentor with id {}", id);
        return this.mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("No mentor found by this id", id));
    }

    @Override
    public Mentor create(MentorDto mentorDTO) {
        final Mentor mentor = mentorConverter.convertToEntity(mentorDTO);
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor update(Long id, MentorDto mentorDTO) {
        final Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("No mentor found by this id", id));
        mentor.setFirstName(mentorDTO.getFirstName());
        mentor.setLastName(mentorDTO.getLastName());
        mentor.setEmail(mentorDTO.getEmail());
        mentor.setPhoneNumber(mentorDTO.getPhoneNumber());
        mentor.setDisplayName(mentorDTO.getDisplayName());
        return mentorRepository.save(mentor);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a mentor with id {}", id);
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException("No mentor found by this id", id);
        }
        mentorRepository.deleteById(id);
        log.info("In deleteById Mentor mentor with id {} successfully deleted", id);
        return true;
    }
}
