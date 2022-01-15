package com.disqo.onboarding_flow_service.service.impl;

import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.ProjectResponseDto;
import com.disqo.onboarding_flow_service.exception.RoadmapNotFoundException;
import com.disqo.onboarding_flow_service.persistance.RoadmapRepository;
import com.disqo.onboarding_flow_service.persistance.entity.Roadmap;
import com.disqo.onboarding_flow_service.persistance.enums.RoadmapStatus;
import com.disqo.onboarding_flow_service.service.MenteeService;
import com.disqo.onboarding_flow_service.service.MentorService;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoadmapServiceImpl implements RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final MenteeService menteeService;
    private final MentorService mentorService;

    @Override
    public List<Roadmap> findALl() {
        log.info("In findAll Roadmap requested to get all roadmaps");
        return this.roadmapRepository.findAll();
    }

    @Override
    public Roadmap findById(Long id) {
        log.info("In findById Roadmap requested to get the roadmap with id {}", id);
        return this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
    }

    @Override
    public Roadmap create(final RoadmapDto roadmapDTO, final ProjectResponseDto jiraProject) {
        log.info("Requested to create a roadmap");
        roadmapDTO.setJiraProjectKey(jiraProject.getKey());
        final Roadmap roadmap = buildFrom(roadmapDTO);
        roadmap.setStatus(RoadmapStatus.STARTED);
        log.info("In create Roadmap roadmap successfully created");
        return roadmapRepository.save(roadmap);
    }

    @Override
    @Transactional
    public Roadmap update(Long id, RoadmapDto roadmapDTO) {
        log.info("Requested to update a talent with id {}", id);
        final Roadmap roadmap = this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
        extracteRoadMap(roadmapDTO, roadmap);
        log.info("In update Roadmap roadmap with id {} successfully updated", id);
        return roadmapRepository.save(roadmap);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a roadmap with id {}", id);
        if (!roadmapRepository.existsById(id)) {
            throw new RoadmapNotFoundException("No roadmap found by this id", id);
        }
        roadmapRepository.deleteById(id);
        log.info("In deleteById Roadmap roadmap with id {} successfully deleted", id);
        return true;
    }

    @Override
    public Roadmap updateStatus(Long id, String status) {
        log.info("Requested to update status to {} of a roadmap with id {}", status, id);
        final Roadmap roadmap = this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
        roadmap.setStatus(RoadmapStatus.valueOf(status.toUpperCase(Locale.ROOT)));
        log.info("In updateStatus Roadmap the status of roadmap with id {} successfully updated to {}", id, status);
        return roadmapRepository.save(roadmap);
    }

    @Override
    public Roadmap findByProjectKey(String projectKey) {
        return this.roadmapRepository.findByJiraProjectKey(projectKey)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this key", projectKey));
    }

    private Roadmap buildFrom(final RoadmapDto roadmapDto) {
        final Roadmap roadmap = new Roadmap();
        extracteRoadMap(roadmapDto, roadmap);
        return roadmap;
    }

    private void extracteRoadMap(final RoadmapDto roadmapDto, final Roadmap roadmap) {
        roadmap.setStartDate(roadmapDto.getStartDate());
        roadmap.setJiraProjectKey(roadmapDto.getJiraProjectKey());
        roadmap.setEndDate(roadmapDto.getEndDate());
        roadmap.setDescription(roadmapDto.getDescription());
        roadmap.setName(roadmapDto.getName());
        roadmap.setMentee(menteeService.findById(roadmapDto.getMenteeId()));
        roadmap.setMentor(mentorService.findById(roadmapDto.getMentorId()));
    }
}
