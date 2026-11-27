package com.dev.demo.DTO.Profile;

import com.dev.demo.Config.Jackson.StrictStringDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.annotation.JsonDeserialize;

/**
 * Equivalent d'un FormRequest Laravel pour la création et la modification
 * d'un profil.
 */
public record ProfileRequest(
        @NotBlank(message = "Le numéro de téléphone est obligatoire")
        @Pattern(regexp = "^[0-9]{1,10}$",
                message = "Le numéro de téléphone doit contenir au maximum 10 chiffres")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String phoneNumber,

        @NotBlank(message = "L'adresse est obligatoire")
        @Size(max = 50, message = "L'adresse ne doit pas dépasser 50 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String address,

        @NotBlank(message = "La ville est obligatoire")
        @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String city,

        @NotBlank(message = "Le code postal est obligatoire")
        @Size(max = 20, message = "Le code postal ne doit pas dépasser 20 caractères")
        @Pattern(regexp = "^[A-Za-z0-9 -]+$", message = "Le code postal est invalide")
        @JsonDeserialize(using = StrictStringDeserializer.class)
        String postalCode) {
}
