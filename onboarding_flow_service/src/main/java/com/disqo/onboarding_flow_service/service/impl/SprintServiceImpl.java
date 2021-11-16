package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.persistance.SprintRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.entity.Sprint;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.SprintService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {

    private final RoadmapService roadmapService;
    private final SprintRepository sprintRepository;

    public SprintServiceImpl(RoadmapService roadmapService, SprintRepository sprintRepository) {
        this.roadmapService = roadmapService;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<Sprint> findALl() {
        return null;
    }

    @Override
    public Sprint findById(Long id) {
        return null;
    }

    @Override
    public Sprint create(SprintDto sprintDto, Roadmap roadmap) {
        final Sprint sprint = new Sprint();

        sprint.setRoadmap(roadmap);
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint update(Long id, SprintDto sprintDto) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

}
