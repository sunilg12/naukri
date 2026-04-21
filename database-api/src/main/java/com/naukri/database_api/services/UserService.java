package com.naukri.database_api.services;

import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Company;
import com.naukri.database_api.models.Profile;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.CompanyRepository;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.LogInRequest;
import com.naukri.database_api.requestDtos.CreateUserRequest;
import com.naukri.database_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final CompanyRepository companyRepository;

    UserService(UserRepository userRepo, JwtUtil jwtUtil, CompanyRepository companyRepository){
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.companyRepository = companyRepository;
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
                .company(null)
                        .profile(buildProfile(request))
                .build();
        return userRepo.save(user);
    }

    //COMPANY ADMIN
    public User saveCompanyAdmin(CreateUserRequest request, Long companyId){

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

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
                .userType(UserRole.ADMIN)
                .company(company)
//                .profile(buildProfile(request))
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

        return jwtUtil.generateToken(user.getName(), user.getId(), user.getUserType());
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

    public boolean existByEmail(String email){
        Optional<User> user = userRepo.findByEmail(email);

        if(user.isEmpty()) return false;

        return true;
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
