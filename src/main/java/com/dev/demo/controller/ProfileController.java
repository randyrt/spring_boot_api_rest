package com.dev.demo.controller;

import com.dev.demo.entity.Profile;
import com.dev.demo.service.ProfileService;

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

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@RequestBody ProfileRequest request,
                                  Authentication authentication) {
        return toResponse(profileService.create(authentication.getName(),
                request.phoneNumber(), request.address(), request.city(), request.postalCode()));
    }

    @GetMapping("/me")
    public ProfileResponse get(Authentication authentication) {
        return toResponse(profileService.findByUsername(authentication.getName()));
    }

    @PutMapping("/me")
    public ProfileResponse update(@RequestBody ProfileRequest request,
                                  Authentication authentication) {
        return toResponse(profileService.update(authentication.getName(),
                request.phoneNumber(), request.address(), request.city(), request.postalCode()));
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication) {
        profileService.delete(authentication.getName());
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
