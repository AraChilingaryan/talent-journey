package com.disqo.talent_service.rest;

import com.disqo.talent_service.converter.TalentConverter;
import com.disqo.talent_service.service.AmazonClientService;
import com.disqo.talent_service.service.TalentService;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import com.disqo.talent_service.service.dto.TalentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/talent")
@RequiredArgsConstructor
public class TalentController {

    private final TalentService talentService;
    private final TalentConverter talentConverter;

    @GetMapping
    public ResponseEntity<List<TalentResponseDTO>> getAll() {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalentResponseDTO> getTalentById(@PathVariable Long id) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TalentResponseDTO> create(@RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.create(talentRequestDTO)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TalentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.update(id, talentRequestDTO)));
    }

    @PutMapping("/update/")
    public ResponseEntity<TalentResponseDTO> updateStatus(@RequestBody TalentRequestDTO dto) {
        return ResponseEntity.ok(talentService.updateStatus(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(talentService.deleteById(id));
    }

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<TalentResponseDTO>> findBySpecialization(@PathVariable Long specializationId) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findBySpecializationId(specializationId)));
    }


    @PostMapping("upload/{talentId}")
    public ResponseEntity<String> uploadAWS(@PathVariable Long talentId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(talentService.addCVForTalent(talentId, file));
    }
}
