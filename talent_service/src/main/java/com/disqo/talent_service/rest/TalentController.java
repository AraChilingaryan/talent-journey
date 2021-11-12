package com.disqo.talent_service.rest;

import com.disqo.talent_service.converter.TalentConverter;
import com.disqo.talent_service.persistance.entity.Talent;
import com.disqo.talent_service.service.AmazonClientService;
import com.disqo.talent_service.service.TalentService;
import com.disqo.talent_service.service.dto.TalentRequestDTO;
import com.disqo.talent_service.service.dto.TalentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/talent")
public class TalentController {

    private final TalentService talentService;
    private final TalentConverter talentConverter;
    private final AmazonClientService amazonClientService;

    public TalentController(TalentService talentService, TalentConverter talentConverter,
                            AmazonClientService amazonClientService) {
        this.talentService = talentService;
        this.talentConverter = talentConverter;
        this.amazonClientService = amazonClientService;
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<TalentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.update(id, talentRequestDTO)));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<TalentResponseDTO> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.updateStatus(id, status)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(talentService.deleteById(id));
    }

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<TalentResponseDTO>> findBySpecialization(@PathVariable Long specializationId) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findBySpecializationId(specializationId)));
    }


    //TODO check this
    @PostMapping("upload/{id}")
    public void uploadAWS(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Talent talent = talentService.findById(id);
        String cvFileName = amazonClientService.uploadFile(file, talent);
        talent.setCvFileName(cvFileName);
//        talentRepository.save(talent);
    }
}
