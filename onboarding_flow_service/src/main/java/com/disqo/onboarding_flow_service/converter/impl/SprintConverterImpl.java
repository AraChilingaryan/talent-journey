package com.disqo.onboarding_flow_service.converter.impl;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.converter.SprintConverter;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SprintConverterImpl implements SprintConverter {
    @Override
    public List<SprintDto> bulkConvertToDTO(List<Sprint> sprints) {
        return null;
    }

    @Override
    public SprintDto convertToDTO(Sprint sprint) {
        return null;
    }

    @Override
    public List<Sprint> bulkConvertToEntity(List<SprintDto> sprintDtos) {

        return null;
    }

    @Override
    public Sprint convertToEntity(SprintDto sprintDto, Roadmap roadmap) {
        final Sprint sprint = new Sprint();
        sprint.setName(sprintDto.getName());
        sprint.setStartDate(parseDate(sprintDto.getStartDate()));
        sprint.setEndDate(parseDate(sprintDto.getEndDate()));
        sprint.setRoadmap(roadmap);
        return null;
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
