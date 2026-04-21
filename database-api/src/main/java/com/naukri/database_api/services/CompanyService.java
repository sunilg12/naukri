package com.naukri.database_api.services;

import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Company;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.CompanyRepository;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.CompanyRequest;
import com.naukri.database_api.requestDtos.CreateRecruiterRequest;
import com.naukri.database_api.requestDtos.CreateUserRequest;
import com.naukri.database_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepo;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepo, UserService userService, JwtUtil jwtUtil, UserRepository userRepository){
        this.companyRepo = companyRepo;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }


    public Company registerCompany(@RequestBody CompanyRequest request){
        companyRepo.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new RuntimeException("Company Already exist");
        });
        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setDescription(request.getDescription());
        company.setWebsiteLink(request.getWebsiteLink());
        company.setCompanySize(request.getCompanySize());
        company.setIndustry(request.getIndustry());

        return companyRepo.save(company);
    }

    public User registerCompanyAdmin(@RequestBody CreateUserRequest request,@PathVariable Long companyId){
        User user = userService.saveCompanyAdmin(request, companyId);

        return user;
    }

    public Company findByName(String name) {
        return companyRepo.findByName(name)
                .orElseThrow();
    }

    public void inviteRecruiter(CreateRecruiterRequest request, String token){
        Claims claim = jwtUtil.extractClaims(token);
        Long adminId = claim.get("userId", Long.class);
        User admin = userService.findById(adminId);

        if(!admin.getUserType().equals(UserRole.ADMIN)){
            throw new RuntimeException("UnAuthorized to Use");
        }

        Company company = admin.getCompany();

        User recruiter = this.mpaRecruiterRequestToUser(request, company);
        String recruiterToken = jwtUtil.generateToken(recruiter.getName(), recruiter.getId(), recruiter.getUserType());

        //send notification to recruiter;

    }

    private User mpaRecruiterRequestToUser(CreateRecruiterRequest request, Company company) {
        User recruiter = new User();
        if(userService.existByEmail(request.getEmail())){
            throw new RuntimeException("Recruiter Already Present");
        }
        recruiter.setName(request.getName());
        recruiter.setUserType(UserRole.RECRUITER);
        recruiter.setPassword(request.getPassword());
        recruiter.setCompany(company);

        userRepository.save(recruiter);

        return recruiter;
    }
}
