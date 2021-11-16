package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import com.disqo.onboarding_flow_service.converter.MenteeConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Mentee;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class MenteeConverterImpl implements MenteeConverter {

    @Override
    public List<MenteeDto> bulkConvertToDto(final List<Mentee> mentees) {
        return mentees.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MenteeDto convertToDto(final Mentee mentee) {
        final MenteeDto menteeDTO = new MenteeDto();
        menteeDTO.setId(mentee.getId());
        menteeDTO.setFirstName(mentee.getFirstName());
        menteeDTO.setLastName(mentee.getLastName());
        menteeDTO.setEmail(mentee.getEmail());
        menteeDTO.setPhoneNumber(mentee.getPhoneNumber());
        menteeDTO.setDisplayName(mentee.getDisplayName());
        menteeDTO.setMentorId(mentee.getMentor().getId());
        return menteeDTO;
    }

    @Override
    public JiraUserDto convertToJiraDTO(final MenteeDto menteeDTO) {
        final JiraUserDto jiraUserDto = new JiraUserDto();
        jiraUserDto.setDisplayName(menteeDTO.getDisplayName());
        jiraUserDto.setEmailAddress(menteeDTO.getEmail());
        return jiraUserDto;
    }
}
