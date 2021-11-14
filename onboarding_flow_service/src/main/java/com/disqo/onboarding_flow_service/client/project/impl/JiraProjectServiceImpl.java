package com.disqo.onboarding_flow_service.client.project.impl;

import com.disqo.onboarding_flow_service.client.project.JiraProjectService;
import com.disqo.onboarding_flow_service.client.project.dto.*;
import com.disqo.onboarding_flow_service.config.JiraIntegrationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class JiraProjectServiceImpl implements JiraProjectService {
    private final static Logger log = LoggerFactory.getLogger(JiraProjectServiceImpl.class);

    private final RestTemplate restTemplate;
    private final JiraIntegrationProperties properties;

    public JiraProjectServiceImpl(final RestTemplate restTemplate,
                                  final JiraIntegrationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public ProjectResponseDto createProject(final ProjectRequestDto project) {
        log.info("Started project create method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<ProjectRequestDto> httpEntity = new HttpEntity<>(project, headers);
        final String finalUrl = properties.getUri() + "/project";
        log.info("Finished project create method");
        return restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, ProjectResponseDto.class).getBody();
    }

    @Override
    public ProjectResponseDto getProject(final String projectKey) {
        log.info("Started getProject method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getUri() + "/project/" + projectKey;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl)
                .queryParam("projectKey", projectKey);
        log.info("Finished getProject method");

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, ProjectResponseDto.class).getBody();
    }

    @Override
    public ProjectRoleResponseDto addUserToProject(final String url, final AssignUserProjectRoleDto user) {
        log.info("Started addUserToProject method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<?> httpEntity = new HttpEntity<>(user, headers);
        log.info("Finished addUserToProject method");
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, ProjectRoleResponseDto.class).getBody();
    }

    @Override
    public ProjectRoleDto getProjectRoles(final String projectKey) {
        log.info("Started getProjectRoles method");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getUsername(), properties.getMyAccessToken());
        final HttpEntity<ProjectRoleDto> httpEntity = new HttpEntity<>(headers);
        final String finalUrl = properties.getUri() + "/project/" + projectKey + "/role";
        log.info("Finished getProjectRoles method");
        return restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, ProjectRoleDto.class).getBody();
    }

}
