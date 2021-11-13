package com.disqo.onboarding_flow_service.client.user;

import com.disqo.onboarding_flow_service.client.user.dto.JiraUserDto;

public interface JiraUserService {

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

}
