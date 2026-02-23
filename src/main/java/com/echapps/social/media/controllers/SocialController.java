package com.echapps.social.media.controllers;


import com.echapps.social.media.models.SocialUser;
import com.echapps.social.media.services.SocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/social")
public class SocialController {
    private SocialService socialService;

    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<SocialUser>> getUsers() {
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser) {
        return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        socialService.deleteUser(userId);
        return new ResponseEntity<>("Deleted " + userId + " successfully", HttpStatus.OK);
    }

}
