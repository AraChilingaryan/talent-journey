package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.annotation.Converter;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.converter.SprintConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Converter
public class SprintConverterImpl implements SprintConverter {
    @Override
    public List<JiraSprintDto> bulkConvertToDTO(List<Sprint> sprints) {
        return null;
    }

    @Override
    public SprintDto convertToDto(Sprint sprint) {
        final SprintDto sprintDto = new SprintDto();
        sprintDto.setId(sprint.getId());
        sprintDto.setRoadMapId(sprint.getRoadmap().getId());
        sprintDto.setEndDate(sprint.getEndDate());
        sprintDto.setStartDate(sprint.getStartDate());
        sprintDto.setName(sprint.getName());
        return sprintDto;
    }

    private Date parseDate(String stringDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        try {
            return inputFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
