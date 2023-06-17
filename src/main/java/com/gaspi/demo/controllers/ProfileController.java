package com.gaspi.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaspi.demo.model.Profile;
import com.gaspi.demo.service.ProfileService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    ProfileService profileService;
    
    @CrossOrigin(origins = "*")
    @GetMapping(path = "")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok().body("Api successfully");
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "welcome")
    public ResponseEntity<Object> welcome(){
        Profile profile = profileService.getProfile();
        return ResponseEntity.ok().body(profile);
    }
}
