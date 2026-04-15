package com.naukri.database_api.services;

import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Profile;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.LogInRequest;
import com.naukri.database_api.requestDtos.CreateUserRequest;
import com.naukri.database_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;

    UserService(UserRepository userRepo, JwtUtil jwtUtil){
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }


    public User saveUser(CreateUserRequest request){
        userRepo.findByName(request.getName()).ifPresent(u -> {
            throw new RuntimeException("User Already registered");
        });
        userRepo.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new RuntimeException("User Already registered");
        });

        buildUserNameIfDoesNotExist(request);
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .userType(UserRole.JOB_SEEKER)
//                        .profile(buildProfile(request))
                .build();
        return userRepo.save(user);
    }

    private Profile buildProfile(CreateUserRequest request) {
        // TODO
        Profile.builder().name(request.getName()).bio(null).build();
        return null;
    }

    private void buildUserNameIfDoesNotExist(CreateUserRequest request) {
        if(Strings.isBlank(request.getName())) {
            request.setName(request.getEmail());
        }
    }

    public String logIn(LogInRequest request){

        validateString(request.getUserName(), "User name is invalid");
        validateString(request.getPassword(), "Invalid password");

        Optional<User> userOpt = userRepo.findByEmailOrName(request.getUserName(), request.getUserName());
        if(userOpt.isEmpty()){
            throw new RuntimeException("User Not Found");
        }

        User user = userOpt.get();
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Password is Incorrect");
        }

        return JwtUtil.generateToken(user.getName(), user.getId());
    }

    private void validateString(String field, String message) {
        if(Strings.isBlank(field)) {
            throw new RuntimeException(message);
        }
    }

    public User findById(Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found"));
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public Claims validateToken(String token) {
        Claims claims = jwtUtil.extractClaims(token);
        // validate claims
        Date now = new Date();
//        claims.getIssuer() < now < claims.getExpiration();
        return claims;
    }

    public void deleteUser(String token) {
        Long id = Long.parseLong((String) validateToken(token).get("userId"));
        userRepo.deleteById(id);
    }
}
