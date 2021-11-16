package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.facade.UserRoadmapRegistrationFacade;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapConverter roadmapConverter;
    private final UserRoadmapRegistrationFacade userRoadmapRegistrationFacade;

    public RoadmapController(RoadmapService roadmapService, RoadmapConverter roadmapConverter,
                             UserRoadmapRegistrationFacade userRoadmapRegistrationFacade) {
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
        this.userRoadmapRegistrationFacade = userRoadmapRegistrationFacade;
    }

    @GetMapping
    public ResponseEntity<List<RoadmapDTO>> getAll() {
        return ResponseEntity.ok(roadmapConverter.bulkConvertToDTO(roadmapService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapDTO> getRoadmapById(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapConverter.convertToDTO(roadmapService.findById(id)));
    }

    @PostMapping("/project")
    public ResponseEntity<RoadmapDTO> createRoadmap(@RequestBody @Valid RoadmapDTO roadmapDTO) {
        return ResponseEntity.ok(userRoadmapRegistrationFacade.createRoadmap(roadmapDTO));
    }

    @PostMapping("/sprint")
    public ResponseEntity<SprintDto> createSprint(@RequestBody @Valid SprintDto sprintDto) {
        return ResponseEntity.ok(userRoadmapRegistrationFacade.createSprint(sprintDto));
    }
}
