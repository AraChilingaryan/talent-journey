package com.disqo.authentication_service.service.auth;


import com.disqo.authentication_service.rest.auth.model.AuthRequest;

public interface AuthService {

    String login(final AuthRequest loginRequest);


}
