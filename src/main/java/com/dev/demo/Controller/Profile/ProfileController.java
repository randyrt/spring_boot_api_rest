package com.dev.demo.Controller.Profile;

import com.dev.demo.DTO.ApiResponse.ApiResponse;
import com.dev.demo.Entity.Profile.Profile;
import com.dev.demo.Service.Profile.ProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProfileResponse> create(@RequestBody ProfileRequest request,
                                               Authentication authentication) {
        ProfileResponse response = toResponse(profileService.create(authentication.getName(),
                request.phoneNumber(), request.address(), request.city(), request.postalCode()));
        return new ApiResponse<>("Profil créé avec succès", response);
    }

    @GetMapping("/get")
    public ApiResponse<ProfileResponse> get(Authentication authentication) {
        return new ApiResponse<>("Profil récupéré avec succès",
                toResponse(profileService.findByUsername(authentication.getName())));
    }

    @PutMapping("/put")
    public ApiResponse<ProfileResponse> update(@RequestBody ProfileRequest request,
                                               Authentication authentication) {
        ProfileResponse response = toResponse(profileService.update(authentication.getName(),
                request.phoneNumber(), request.address(), request.city(), request.postalCode()));
        return new ApiResponse<>("Profil mis à jour avec succès", response);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> delete(Authentication authentication) {
        profileService.delete(authentication.getName());
        return new ApiResponse<>("Profil supprimé avec succès", null);
    }

    private ProfileResponse toResponse(Profile profile) {
        return new ProfileResponse(profile.getId(), profile.getPhoneNumber(),
                profile.getAddress(), profile.getCity(), profile.getPostalCode());
    }

    public record ProfileRequest(String phoneNumber, String address,
                                 String city, String postalCode) {
    }

    public record ProfileResponse(Long id, String phoneNumber, String address,
                                  String city, String postalCode) {
    }
}
