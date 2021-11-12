package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.converter.RoadmapConverter;
import com.disqo.onboarding_flow_service.service.RoadmapService;
import com.disqo.onboarding_flow_service.service.dto.RoadmapDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapConverter roadmapConverter;

    public RoadmapController(RoadmapService roadmapService, RoadmapConverter roadmapConverter) {
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
    }

    @GetMapping
    public ResponseEntity<List<RoadmapDTO>> getAll() {
        return ResponseEntity.ok(roadmapConverter.bulkConvertToDTO(roadmapService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapDTO> getRoadmapById(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapConverter.convertToDTO(roadmapService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RoadmapDTO> create(@RequestBody @Valid RoadmapDTO roadmapDTO) {
        return ResponseEntity.ok(roadmapConverter.convertToDTO(roadmapService.create(roadmapDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoadmapDTO> update(@PathVariable Long id, @RequestBody @Valid RoadmapDTO roadmapDTO) {
        return ResponseEntity.ok(roadmapConverter.convertToDTO(roadmapService.update(id, roadmapDTO)));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<RoadmapDTO> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(roadmapConverter.convertToDTO(roadmapService.updateStatus(id, status)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapService.deleteById(id));
    }
}
