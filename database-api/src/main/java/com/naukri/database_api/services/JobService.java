package com.naukri.database_api.services;

import com.naukri.database_api.enums.JobStatus;
import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Job;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.JobRepository;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.CreateJobRequest;
import com.naukri.database_api.requestDtos.JobSearchRequest;
import com.naukri.database_api.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class JobService {

    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    JobService(JobRepository jobRepo, UserRepository userRepo, JwtUtil jwtUtil,
               UserService userService){
        this.jobRepo = jobRepo;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public Job createJob(CreateJobRequest request, Long recruiterId){
        User recruiter = userRepo.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Invalid user"));
        if(recruiter.getUserType() != UserRole.RECRUITER){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized User");
        }

        Job job = new Job();
        job.setLocation(request.getLocation());
        job.setJobDescription(request.getJobDescription());
        job.setSkills(request.getSkills());
        job.setMinExperience(request.getMinExperience());
        job.setMaxExperience(request.getMaxExperience());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());

        job.setCreatedBy(recruiter);
        job.setStatus(JobStatus.ACTIVE);

        return jobRepo.save(job);
    }

    public Job findById(Long jobId){
        return jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not Found"));
    }

    public Job updateStatus(String token, Long jobId, String status ){
        //verify the user by token;
        Claims claims = JwtUtil.extractClaims(token);
        User user = userService.findById(Long.valueOf(claims.getId()));

        if(user.getUserType() != UserRole.RECRUITER){
            throw new RuntimeException("User not found");
        }
        //----------------------------------------------------------

        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not Found"));
        if(status.equalsIgnoreCase("ACTIVE")){
            job.setStatus(JobStatus.ACTIVE);
        }

        if(status.equalsIgnoreCase("INACTIVE")){
            job.setStatus(JobStatus.INACTIVE);
        }

        if(status.equalsIgnoreCase("EXPIRED")){
            job.setStatus(JobStatus.EXPIRED);
        }

        jobRepo.save(job);

        return job;
    }


    public List<Job> searchJobs(JobSearchRequest request) {
        return jobRepo.searchJob(request.getSkills(),request.getLocation(),
                request.getMinExperience(), request.getMaxExperience(), request.getMinSalary(),
                request.getMaxSalary());
    }
}
