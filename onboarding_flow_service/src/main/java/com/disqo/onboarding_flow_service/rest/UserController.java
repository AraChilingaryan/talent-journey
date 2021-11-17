package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.facade.UserFacade;
import com.disqo.onboarding_flow_service.service.dto.MenteeDto;
import com.disqo.onboarding_flow_service.service.dto.MentorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/mentee")
    public ResponseEntity<MenteeDto> createMentee(@RequestBody MenteeDto menteeDto) {
        return ResponseEntity.ok(this.userFacade.createMentee(menteeDto));
    }
    
    @GetMapping("/mentor")
    public ResponseEntity<List<MentorDto>> getAll() {
        return ResponseEntity.ok(this.userFacade.getAllMentors());
    }

    @GetMapping("/mentee")
    public ResponseEntity<List<MenteeDto>> getAllMentees() {
        return ResponseEntity.ok(this.userFacade.getAllMentees());
    }

    @GetMapping("/mentee/{id}")
    public ResponseEntity<MenteeDto> findMenteeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userFacade.findMenteeById(id));
    }

    @GetMapping("/mentor/{id}")
    public ResponseEntity<MentorDto> findMentorById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userFacade.findMentorById(id));
    }

    @DeleteMapping("mentee/{id}")
    public void deleteMentee(@PathVariable Long id) {
        this.userFacade.deleteMentee(id);
    }

    @DeleteMapping("mentor/{id}")
    public void deleteMentor(@PathVariable Long id) {
        this.userFacade.deleteMentor(id);
    }

}
