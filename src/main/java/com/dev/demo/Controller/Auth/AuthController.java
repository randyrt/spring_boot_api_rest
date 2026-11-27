package com.dev.demo.Controller.Auth;

import java.util.Map;

import com.dev.demo.DTO.UserDTOValidator.LoginRequestDTOValidator;
import com.dev.demo.DTO.UserDTOValidator.RegisterRequestDTOValidator;
import com.dev.demo.Service.Auth.AuthService;
import jakarta.validation.Valid;
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
    public Map<String, Object> login(@Valid @RequestBody LoginRequestDTOValidator request) {
        return authService.login(request.username(), request.password(), request.email());
    }

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequestDTOValidator request) {
        return authService.register(request.username(), request.email(), request.password());
    }

}
