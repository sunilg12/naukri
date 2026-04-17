package com.naukri.database_api.controllers;

import com.naukri.database_api.enums.JobStatus;
import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.exception.UnAuthorizedException;
import com.naukri.database_api.models.Job;
import com.naukri.database_api.models.JobApplication;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.CreateJobRequest;
import com.naukri.database_api.requestDtos.JobApplicationRequest;
import com.naukri.database_api.requestDtos.JobSearchRequest;
import com.naukri.database_api.security.JwtUtil;
import com.naukri.database_api.services.JobService;
import com.naukri.database_api.services.UserService;
import io.jsonwebtoken.Claims;
import jdk.jfr.Frequency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;
    private final UserRepository userRepo;
    private final UserService userService;

    JobController(JobService jobService, UserRepository userRepo, UserService userService){
        this.jobService = jobService;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createJob(CreateJobRequest request, Long recruiterId){
        jobService.createJob(request, recruiterId);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestHeader String token, Long jobId, String status){
        //verify the user by token;
        Job job = jobService.updateStatus(token, jobId, status);
        return ResponseEntity.status(HttpStatus.OK).body(job);
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestBody JobSearchRequest request){
        return jobService.searchJobs(request);
    }
}
