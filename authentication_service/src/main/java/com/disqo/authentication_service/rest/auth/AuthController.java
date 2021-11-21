package com.disqo.authentication_service.rest.auth;

import com.disqo.authentication_service.rest.auth.model.AuthResponseDTO;
import com.disqo.authentication_service.rest.auth.model.AuthRequest;
import com.disqo.authentication_service.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@RequestBody final AuthRequest loginRequest) {
        return ResponseEntity.ok(new AuthResponseDTO(authService.login(loginRequest)));
    }

}
