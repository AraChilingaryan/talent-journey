package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.facade.RoadMapFacade;
import com.disqo.onboarding_flow_service.facade.SprintFacade;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDto;
import com.disqo.onboarding_flow_service.service.dto.SprintDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapConverter roadmapConverter;
    private final SprintFacade sprintFacade;
    private final RoadMapFacade roadMapFacade;

    public RoadmapController(RoadmapService roadmapService,
                             RoadmapConverter roadmapConverter,
                             SprintFacade sprintFacade,
                             RoadMapFacade roadMapFacade) {
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
        this.sprintFacade = sprintFacade;
        this.roadMapFacade = roadMapFacade;
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
        return ResponseEntity.ok(roadMapFacade.create(roadmapDTO));
    }

    @PostMapping("/sprint")
    public ResponseEntity<SprintDto> createSprint(@RequestBody @Valid SprintDto sprintDto) {
        return ResponseEntity.ok(sprintFacade.create(sprintDto));
    }
}
