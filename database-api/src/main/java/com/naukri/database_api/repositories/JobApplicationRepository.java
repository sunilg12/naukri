package com.naukri.database_api.repositories;

import com.naukri.database_api.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    @Query("""
            SELECT ja
            FROM JobApplication ja
            WHERE ja.job.id = : jobId
            """)
    List<JobApplication> getApplicationsBasedOnJobId(Long jobId);
}
