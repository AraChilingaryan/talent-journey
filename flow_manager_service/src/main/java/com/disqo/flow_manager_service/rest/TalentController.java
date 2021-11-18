package com.disqo.flow_manager_service.rest;

import com.disqo.flow_manager_service.client.TalentClient;
import com.disqo.flow_manager_service.rest.dto.TalentDTO;
import com.disqo.flow_manager_service.service.TalentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/talent")
public class TalentController {


    private final TalentClient talentClient;
    private final TalentService talentService;

    public TalentController(TalentClient talentClient, TalentService talentService) {
        this.talentClient = talentClient;
        this.talentService = talentService;
    }

    @GetMapping
    public TalentDTO getTalent(@PathVariable String email) {
        return talentService.getTalent(email);
    }

    @PutMapping
    public ResponseEntity<TalentDTO> sendTalent(@RequestBody TalentDTO talentDTO){
        return ResponseEntity.ok(talentClient.sendTalent(talentDTO));
    }
}
