package com.disqo.onboarding_flow_service.client.sprint.impl;

import com.disqo.onboarding_flow_service.client.project.dto.ProjectRoleDto;
import com.disqo.onboarding_flow_service.client.sprint.JiraSprintService;
import com.disqo.onboarding_flow_service.client.sprint.dto.SprintDto;
import com.disqo.onboarding_flow_service.config.JiraIntegrationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JiraSprintServiceImpl implements JiraSprintService {

    private final static Logger log = LoggerFactory.getLogger(JiraSprintServiceImpl.class);

    private final RestTemplate restTemplate;
    private final JiraIntegrationProperties properties;

    public JiraSprintServiceImpl(final RestTemplate restTemplate,
                                 final JiraIntegrationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public SprintDto createSprint(final SprintDto sprint) {
        log.info("Started createSprint method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<SprintDto> httpEntity = new HttpEntity<>(headers);
        //TODO nor complete
        final String finalUrl = properties.getUri() + "/project/" + "/role";
        final SprintDto response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, SprintDto.class).getBody();
        log.info("Finished getProjectRoles method");
        return response;
    }
}
