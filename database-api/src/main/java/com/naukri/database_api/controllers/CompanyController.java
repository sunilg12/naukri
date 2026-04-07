package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.requestDtos.CompanyRequest;
import com.naukri.database_api.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyRequest request){
        Company company = new Company();

        return ResponseEntity.status(201).body(company);
    }
}
