package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.converter.MentorConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.persistance.entity.Mentor;
import com.disqo.onboarding_flow_service.persistance.entity.User;
import com.disqo.onboarding_flow_service.service.dto.MenteeDTO;
import com.disqo.onboarding_flow_service.service.dto.MentorDTO;
import com.disqo.onboarding_flow_service.service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MentorConverterImpl implements MentorConverter {

    @Override
    public List<MentorDTO> bulkConvertToDTO(List<Mentor> mentors) {
        return mentors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MentorDTO convertToDTO(Mentor mentor) {
        final MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(mentor.getId());
        mentorDTO.setFirstName(mentor.getFirstName());
        mentorDTO.setLastName(mentor.getLastName());
        mentorDTO.setEmail(mentor.getEmail());
        mentorDTO.setPhoneNumber(mentor.getPhoneNumber());
        mentorDTO.setDisplayName(mentor.getDisplayName());
        mentorDTO.setAccountId(mentor.getAccountId());
        mentorDTO.setSelf(mentor.getSelf());
        return mentorDTO;
    }

    @Override
    public List<Mentor> bulkConvertToEntity(List<MentorDTO> mentorDTOS) {
        return mentorDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Mentor convertToEntity(MentorDTO mentorDTO) {
        final Mentor mentor = new Mentor();
        mentor.setFirstName(mentorDTO.getFirstName());
        mentor.setLastName(mentorDTO.getLastName());
        mentor.setEmail(mentorDTO.getEmail());
        mentor.setPhoneNumber(mentorDTO.getPhoneNumber());
        mentor.setDisplayName(mentorDTO.getDisplayName());
        mentor.setAccountId(mentorDTO.getAccountId());
        mentor.setSelf(mentorDTO.getSelf());
        return mentor;
    }
}
