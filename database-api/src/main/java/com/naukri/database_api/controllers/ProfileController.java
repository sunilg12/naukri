package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Profile;
import com.naukri.database_api.requestDtos.ProfileRequest;
import com.naukri.database_api.services.ProfileService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    ProfileService profileService;

    ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequest request){
        Profile userProfile = profileService.saveProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userProfile);
    }

    @PatchMapping("/patch")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest request){
        profileService.updateProfile(request);

        return ResponseEntity.status(HttpStatus.OK).body(request);
    }
}
