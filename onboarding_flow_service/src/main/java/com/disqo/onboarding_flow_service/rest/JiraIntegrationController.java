package com.disqo.onboarding_flow_service.rest;

import com.disqo.onboarding_flow_service.client.jiraclient.JiraIntegrationClientFacade;
import com.disqo.onboarding_flow_service.client.jiraclient.project.dto.*;
import com.disqo.onboarding_flow_service.client.jiraclient.sprint.dto.JiraSprintDto;
import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jira")
public class JiraIntegrationController {

    private final JiraIntegrationClientFacade jiraIntegrationClientFacade;


    public JiraIntegrationController(final JiraIntegrationClientFacade jiraIntegrationClientFacade) {
        this.jiraIntegrationClientFacade = jiraIntegrationClientFacade;
    }

    @PostMapping("/project")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto projectRequestDto) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.createProject(projectRequestDto));
    }

    @GetMapping("/project/{projectKey}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable String projectKey) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.getProject(projectKey));
    }

    @PostMapping("/user")
    public ResponseEntity<JiraUserDto> createUser(@RequestBody JiraUserDto userDto) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.createUser(userDto));
    }

    @DeleteMapping("/user/{accountId}")
    public void delete(@PathVariable String accountId) {
        this.jiraIntegrationClientFacade.deleteUser(accountId);
    }

    @PostMapping("/project/{projectKey}")
    public ResponseEntity<ProjectRoleResponseDto> addUserToProject(@PathVariable String projectKey,
                                                                   @RequestBody AssignUserProjectRoleDto user) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.addUserToProject(projectKey, user));
    }

    @GetMapping("/project/board")
    public ResponseEntity<ProjectBoardDto> getProjectBoard(@RequestParam("projectId") String projectId,
                                                           @RequestParam("name") String projectName) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.getProjectBoards(projectId, projectName));
    }

    @PostMapping("/sprint")
    public ResponseEntity<JiraSprintDto> getProjectBoard(@RequestBody JiraSprintDto jiraSprintDto) {
        return ResponseEntity.ok(this.jiraIntegrationClientFacade.createSprint(jiraSprintDto));
    }

    @DeleteMapping("/sprint/{sprintId}")
    public void getProjectBoard(@PathVariable Long sprintId) {
        this.jiraIntegrationClientFacade.deleteSprint(sprintId);
    }
}
