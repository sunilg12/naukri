package com.naukri.database_api.controllers;

import com.naukri.database_api.models.User;
import com.naukri.database_api.requestDtos.LogInRequest;
import com.naukri.database_api.requestDtos.UserRequest;
import com.naukri.database_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private  UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest request){
        User user = userService.saveUser(request);

        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInRequest request){
        User user = userService.logIn(request);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/id/{id}")
    public User getById(@PathVariable int id){
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        return userService.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not found"));
    }
}
