package com.dev.demo.DTO.UserDTOValidator;

import com.dev.demo.Config.Jackson.StrictStringDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.annotation.JsonDeserialize;

public record LoginRequestDTOValidator(
        @NotBlank(message = "Le username est obligatoire")
        @Size(max = 50, message = "Le username ne doit pas dépasser 50 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String username,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String password,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email doit être valide")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String email) {
}
