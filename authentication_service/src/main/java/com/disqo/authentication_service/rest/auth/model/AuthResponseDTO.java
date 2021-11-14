package com.disqo.authentication_service.rest.auth.model;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {

    private String accessToken;

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
