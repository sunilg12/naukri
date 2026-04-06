package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
