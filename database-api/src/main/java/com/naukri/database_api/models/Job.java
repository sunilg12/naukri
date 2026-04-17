package com.naukri.database_api.models;


import com.naukri.database_api.enums.JobStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_job_location", columnList = "location"),
                @Index(name = "idx_job_exp", columnList = "minExperience, maxExperience"),
                @Index(name = "idx_job_salary", columnList = "minSalary, maxSalary"),
                @Index(name = "idx_job_createAt", columnList = "createAt")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    private User createdBy;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    private String location;

    private Integer minExperience;

    private Integer maxExperience;

    private double minSalary;

    private double maxSalary;

    @Column
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @CreationTimestamp
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
