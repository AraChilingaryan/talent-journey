package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapConverter roadmapConverter;

    public RoadmapController(RoadmapService roadmapService, RoadmapConverter roadmapConverter) {
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
    }

    @GetMapping
    public ResponseEntity<List<RoadmapDto>> getAll() {
        return ResponseEntity.ok(roadmapConverter.bulkConvertToDTO(roadmapService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapDto> getRoadmapById(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapConverter.convertToDto(roadmapService.findById(id)));
    }

    @PostMapping("/project")
    public ResponseEntity<RoadmapDto> createRoadmap(@RequestBody @Valid RoadmapDto roadmapDTO) {
        return null;
    }

    @PostMapping("/sprint")
    public ResponseEntity<JiraSprintDto> createSprint(@RequestBody @Valid JiraSprintDto jiraSprintDto) {
        return null;
    }
}
