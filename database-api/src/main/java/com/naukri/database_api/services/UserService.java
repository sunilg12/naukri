package com.naukri.database_api.services;

import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.LogInRequest;
import com.naukri.database_api.requestDtos.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User saveUser(@RequestBody UserRequest request){
        userRepo.findByEmail(request.getEmail()).ifPresent(u -> {
            new RuntimeException("User Already registered");
        });
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setSkills(request.getSkills());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepo.save(user);
    }

    public User logIn(LogInRequest request){
        Optional<User> userOpt = userRepo.findByEmailOrPhoneNumber(request.getUserName(), request.getUserName());

        if(userOpt.isEmpty()){
            throw new RuntimeException("User Not Found");
        }

        User user = userOpt.get();

        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Password is Incorrect");
        }

        return user;
    }

    public Optional<User> findById(int id){
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
