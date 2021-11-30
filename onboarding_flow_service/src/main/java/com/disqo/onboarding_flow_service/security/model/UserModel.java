package com.disqo.onboarding_flow_service.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class UserModel {

    private String username;
    private List<LinkedHashMap<String, String>> roles;
}
