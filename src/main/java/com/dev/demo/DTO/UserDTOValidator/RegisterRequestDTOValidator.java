package com.dev.demo.DTO.UserDTOValidator;

import com.dev.demo.Config.Jackson.StrictStringDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.annotation.JsonDeserialize;

public record RegisterRequestDTOValidator(
        @NotBlank(message = "Le username est obligatoire")
        @Size(min = 3, max = 50,
                message = "Le username doit contenir entre 3 et 50 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String username,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email doit être valide")
        @Size(max = 255, message = "L'email ne doit pas dépasser 255 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 8, max = 100,
                message = "Le mot de passe doit contenir entre 8 et 100 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String password) {
}
