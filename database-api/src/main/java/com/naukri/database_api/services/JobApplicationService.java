package com.naukri.database_api.services;

import com.naukri.database_api.enums.JobStatus;
import com.naukri.database_api.models.Job;
import com.naukri.database_api.models.JobApplication;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.JobApplicationRepository;
import com.naukri.database_api.requestDtos.JobApplicationRequest;
import com.naukri.database_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final JobService jobService;
    private final JobApplicationRepository jobApplicationRepo;



    JobApplicationService(UserService userService, JwtUtil jwtUtil, JobService jobService,
                          JobApplicationRepository jobApplicationRepo){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.jobService = jobService;
        this.jobApplicationRepo = jobApplicationRepo;
    }


    public JobApplication applyJob(String token, JobApplicationRequest request){

        Claims claims = JwtUtil.extractClaims(token);
        User user = userService.findById(Long.parseLong(claims.getId()));

        if(jwtUtil.isValidToken(token)){
            throw new RuntimeException("Session expired");
        }
        Job job = jobService.findById(request.getJobId());
        JobStatus status = job.getStatus();

        if(status != JobStatus.ACTIVE){
            throw new RuntimeException("Job Not Available");
        }

        JobApplication ja = new JobApplication();

        ja.setJob(job);
        ja.setApplicant(user);
        ja.setPhoneNumber(request.getPhoneNumber());

        jobApplicationRepo.save(ja);

        return ja;
    }

    public List<JobApplication> getApplicationsBasedOnJobId(Long id) {
        return jobApplicationRepo.getApplicationsBasedOnJobId(id);
    }
}
