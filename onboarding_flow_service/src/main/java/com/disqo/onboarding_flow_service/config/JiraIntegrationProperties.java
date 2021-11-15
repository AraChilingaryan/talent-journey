package com.disqo.onboarding_flow_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/jira.properties")
@ConfigurationProperties(prefix = "jira")
public class JiraIntegrationProperties {

    private String uri;
    private String myAccessToken;
    private String username;
    private String sprintUri;
    private String projectUri;
    private String projectBoardUri;

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getMyAccessToken() {
        return myAccessToken;
    }

    public void setMyAccessToken(final String myAccessToken) {
        this.myAccessToken = myAccessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getSprintUri() {
        return sprintUri;
    }

    public void setSprintUri(final String sprintUri) {
        this.sprintUri = sprintUri;
    }

    public String getProjectUri() {
        return projectUri;
    }

    public void setProjectUri(final String projectUri) {
        this.projectUri = projectUri;
    }

    public String getProjectBoardUri() {
        return projectBoardUri;
    }

    public void setProjectBoardUri(final String projectBoardUri) {
        this.projectBoardUri = projectBoardUri;
    }
}
