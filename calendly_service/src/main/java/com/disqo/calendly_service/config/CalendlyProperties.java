package com.disqo.calendly_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/calendly.properties")
@ConfigurationProperties(prefix="calendly")
public class CalendlyProperties {

    private String myAccessToken;
    private String url;
    private String scheduledEvent;

    public String getMyAccessToken() {
        return myAccessToken;
    }

    public void setMyAccessToken(final String myAccessToken) {
        this.myAccessToken = myAccessToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getScheduledEvent() {
        return scheduledEvent;
    }

    public void setScheduledEvent(final String scheduledEvent) {
        this.scheduledEvent = scheduledEvent;
    }
}