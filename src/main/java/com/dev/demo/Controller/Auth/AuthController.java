package com.dev.demo.Controller.Auth;

import java.util.Map;

import com.dev.demo.Service.Auth.AuthService;
import com.dev.demo.Config.Jackson.StrictStringDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.annotation.JsonDeserialize;
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
    public Map<String, Object> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request.username(), request.password(), request.email());
    }

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request.username(), request.email(), request.password());
    }

    public record LoginRequest(
            @NotBlank(message = "Le username est obligatoire")
            @Size(max = 50, message = "Le username ne doit pas dépasser 50 caractères")
            @JsonDeserialize(using = StrictStringDeserializer.class) String username,
            @NotBlank(message = "Le mot de passe est obligatoire")
            @JsonDeserialize(using = StrictStringDeserializer.class) String password,
            @NotBlank(message = "L'email est obligatoire")
            @Email(message = "L'email doit être valide")
            @JsonDeserialize(using = StrictStringDeserializer.class) String email) {
    }

    public record RegisterRequest(
            @NotBlank(message = "Le username est obligatoire")
            @Size(min = 3, max = 50, message = "Le username doit contenir entre 3 et 50 caractères")
            @JsonDeserialize(using = StrictStringDeserializer.class) String username,
            @NotBlank(message = "L'email est obligatoire")
            @Email(message = "L'email doit être valide")
            @Size(max = 255, message = "L'email ne doit pas dépasser 255 caractères")
            @JsonDeserialize(using = StrictStringDeserializer.class) String email,
            @NotBlank(message = "Le mot de passe est obligatoire")
            @Size(min = 8, max = 100, message = "Le mot de passe doit contenir entre 8 et 100 caractères")
            @JsonDeserialize(using = StrictStringDeserializer.class) String password) {
    }
}
