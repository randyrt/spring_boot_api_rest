package com.dev.demo.service;

import com.dev.demo.entity.Profile;
import com.dev.demo.entity.User;
import com.dev.demo.repository.ProfileRepository;
import com.dev.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository,
                          UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile create(String username, String phoneNumber, String address,
                          String city, String postalCode) {
        User user = findUser(username);
        if (user.getProfile() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Un profil existe déjà pour cet utilisateur");
        }

        return profileRepository.save(
                new Profile(user, phoneNumber, address, city, postalCode));
    }

    public Profile findByUsername(String username) {
        User user = findUser(username);
        return profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profil introuvable"));
    }

    public Profile update(String username, String phoneNumber, String address,
                          String city, String postalCode) {
        Profile profile = findByUsername(username);
        profile.setPhoneNumber(phoneNumber);
        profile.setAddress(address);
        profile.setCity(city);
        profile.setPostalCode(postalCode);
        return profileRepository.save(profile);
    }

    public void delete(String username) {
        profileRepository.delete(findByUsername(username));
    }

    private User findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    }
}
