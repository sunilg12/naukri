package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Job;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.CreateJobRequest;
import com.naukri.database_api.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;
    private final UserRepository userRepo;

    JobController(JobService jobService, UserRepository userRepo){
        this.jobService = jobService;
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> createJob(CreateJobRequest request, Long recruiterId){
        jobService.createJob(request, recruiterId);
        return ResponseEntity.status(201).build();
    }
}
