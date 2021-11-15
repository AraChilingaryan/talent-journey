package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;

import java.util.List;
import java.util.stream.Collectors;


public class MenteeConverterImpl implements MenteeConverter {
    private final MentorService mentorService;

    public MenteeConverterImpl(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @Override
    public List<MenteeDTO> bulkConvertToDTO(List<Mentee> mentees) {
        return mentees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MenteeDTO convertToDTO(Mentee mentee) {
        final MenteeDTO menteeDTO = new MenteeDTO();
        menteeDTO.setId(mentee.getId());
        menteeDTO.setFirstName(mentee.getFirstName());
        menteeDTO.setLastName(mentee.getLastName());
        menteeDTO.setEmail(mentee.getEmail());
        menteeDTO.setPhoneNumber(mentee.getPhoneNumber());
        menteeDTO.setDisplayName(mentee.getDisplayName());
        menteeDTO.setAccountId(mentee.getAccountId());
        menteeDTO.setSelf(mentee.getSelf());
        menteeDTO.setMentorId(mentee.getMentor().getId());
        return menteeDTO;
    }

    @Override
    public List<Mentee> bulkConvertToEntity(List<MenteeDTO> menteeDTOS) {
        return menteeDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Mentee convertToEntity(MenteeDTO menteeDTO) {
        final Mentee mentee = new Mentee();
        mentee.setFirstName(menteeDTO.getFirstName());
        mentee.setLastName(menteeDTO.getLastName());
        mentee.setEmail(menteeDTO.getEmail());
        mentee.setPhoneNumber(menteeDTO.getPhoneNumber());
        mentee.setDisplayName(menteeDTO.getDisplayName());
        mentee.setAccountId(menteeDTO.getAccountId());
        mentee.setSelf(menteeDTO.getSelf());
        mentee.setMentor(mentorService.findById(menteeDTO.getMentorId()));
        return mentee;
    }
}
