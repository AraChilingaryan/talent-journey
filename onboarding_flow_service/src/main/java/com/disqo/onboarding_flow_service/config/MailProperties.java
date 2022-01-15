package com.disqo.onboarding_flow_service.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/mail.properties")
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
public class MailProperties {

    private String baseUrl;
    private String path;
}
