package com.naukri.database_api.services;

import com.naukri.database_api.enums.JobStatus;
import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Job;
import com.naukri.database_api.models.User;
import com.naukri.database_api.repositories.JobRepository;
import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.requestDtos.CreateJobRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JobService {

    private final JobRepository jobRepo;
    private final UserRepository userRepo;

    JobService(JobRepository jobRepo, UserRepository userRepo){
        this.jobRepo = jobRepo;
        this.userRepo = userRepo;
    }

    public void createJob(CreateJobRequest request, Long recruiterId){
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

//        return jobRepo.save(job);
    }
}
