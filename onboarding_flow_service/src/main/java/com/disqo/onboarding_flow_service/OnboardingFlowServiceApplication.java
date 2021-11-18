package com.disqo.onboarding_flow_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnboardingFlowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingFlowServiceApplication.class, args);
    }

}
