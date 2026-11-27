package com.dev.demo.Auth;

import java.util.Map;

import com.dev.demo.Service.Auth.AuthService;
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
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        return authService.login(request.username(), request.password(), request.email());
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest request) {
        return authService.register(request.username(), request.email(), request.password());
    }

    public record LoginRequest(String username, String password, String email) {
    }

    public record RegisterRequest(String username, String email, String password) {
    }
}
