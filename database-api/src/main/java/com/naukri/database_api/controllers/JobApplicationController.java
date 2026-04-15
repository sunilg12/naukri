package com.naukri.database_api.controllers;

import com.naukri.database_api.models.JobApplication;
import com.naukri.database_api.requestDtos.JobApplicationRequest;
import com.naukri.database_api.services.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobApplication")
public class JobApplicationController {

    JobApplicationService jobApplicationService;

    JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyJob(@RequestHeader String token, @RequestBody JobApplicationRequest request){
        JobApplication ja = jobApplicationService.applyJob(token, request);

        return  ResponseEntity.status(HttpStatus.OK).body(ja);
    }

}
