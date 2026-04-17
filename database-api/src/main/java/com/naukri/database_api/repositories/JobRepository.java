package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Job;
import com.naukri.database_api.requestDtos.JobSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("""
    SELECT j FROM Job j
    LEFT JOIN j.skills s
    WHERE (:skills IS NULL OR LOWER(s.name) IN :skills)
    And (:location IS NULL OR j.location = :location)
    AND (:minExp IS NULL OR j.minExperience >= :minExp)
    AND (:maxExp IS NULL OR j.maxExperience <= :maxExp)
    AND (:minSalary IS NULL OR j.minSalary >= :minSalary)
    AND (:maxSalary IS NULL OR j.maxSalary <= :maxSalary)
""")
    List<Job> searchJob(
            @Param("skills") List<String> skills,
            @Param("location") String location,
            @Param("minExp") Integer minExp,
            @Param("maxExp") Integer maxExp,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary
    );
}
