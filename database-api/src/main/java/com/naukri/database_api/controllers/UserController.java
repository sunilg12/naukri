package com.naukri.database_api.controllers;

import com.naukri.database_api.models.User;
import com.naukri.database_api.requestDtos.LogInRequest;
import com.naukri.database_api.requestDtos.CreateUserRequest;
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
    public ResponseEntity<?> saveUser(@RequestBody CreateUserRequest request){
        userService.saveUser(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInRequest request){
        return ResponseEntity.ok(userService.logIn(request));
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestHeader String token) {
        userService.deleteUser(token);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/id/{id}")
    public User getById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        return userService.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not found"));
    }
}
