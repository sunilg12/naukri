package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.models.User;
import com.naukri.database_api.requestDtos.CompanyRequest;
import com.naukri.database_api.requestDtos.CreateRecruiterRequest;
import com.naukri.database_api.requestDtos.CreateUserRequest;
import com.naukri.database_api.services.CompanyService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyRequest request){
        Company company = companyService.registerCompany(request);
        User admin = companyService.registerCompanyAdmin(request.getAdmin(),company.getId());

        return ResponseEntity.status(201).body(company);
    }


//    @PostMapping("/create")
//    public void inviteRecruiter(@RequestBody CreateRecruiterRequest request, String token){
//        companyService.inviteRecruiter(request, token);
//    }

    @GetMapping("/getByName")
    public Company findById(@RequestParam String name){
        return companyService.findByName(name);
    }
}
