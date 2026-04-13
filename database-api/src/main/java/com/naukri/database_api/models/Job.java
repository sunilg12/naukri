package com.naukri.database_api.models;


import com.naukri.database_api.enums.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
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
    private List<Skill> skills;

    private String location;

    private Integer minExperience;

    private Integer maxExperience;

    private double minSalary;

    private double maxSalary;

    private String jobDescription;

    private JobStatus status;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}
