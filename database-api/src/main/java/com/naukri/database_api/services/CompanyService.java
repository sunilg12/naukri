package com.naukri.database_api.services;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.repositories.CompanyRepository;
import com.naukri.database_api.requestDtos.CompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepo;

    public CompanyService(CompanyRepository companyRepo){
        this.companyRepo = companyRepo;
    }


    public Company registerCompany(@RequestBody CompanyRequest request){
        companyRepo.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new RuntimeException("Company Already exist");
        });
        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setWebsiteLink(request.getWebsiteLink());
        company.setCompanySize(request.getCompanySize());
        company.setIndustry(request.getIndustry());

        return companyRepo.save(company);
    }
}
