package com.disqo.onboarding_flow_service.client.jiraclient.user;

import com.disqo.onboarding_flow_service.client.jiraclient.user.dto.JiraUserDto;

public interface JiraUserService {

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

}
