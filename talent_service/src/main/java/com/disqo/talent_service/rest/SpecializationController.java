package com.disqo.talent_service.rest;

import com.disqo.talent_service.converter.SpecializationConverter;
import com.disqo.talent_service.service.dto.SpecializationRequestDTO;
import com.disqo.talent_service.service.dto.SpecializationResponseDTO;
import com.disqo.talent_service.service.SpecializationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/specialization")
public class SpecializationController {

    private final SpecializationService specializationService;
    private final SpecializationConverter specializationConverter;

    public SpecializationController(SpecializationService specializationService, SpecializationConverter specializationConverter) {
        this.specializationService = specializationService;
        this.specializationConverter = specializationConverter;
    }

    @GetMapping
    public ResponseEntity<List<SpecializationResponseDTO>> getAll() {
        return ResponseEntity.ok(specializationConverter.bulkConvertToDTO(specializationService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationResponseDTO> getSpecializationById(@PathVariable Long id){
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<SpecializationResponseDTO> create(@RequestBody @Valid SpecializationRequestDTO specializationRequestDTO){
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.create(specializationRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecializationResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SpecializationRequestDTO specializationRequestDTO){
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.update(id,specializationRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(specializationService.deleteById(id));
    }
}
