package com.naukri.database_api.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int id;

    private User createdBy;

    private List<Skill> skills;

    private String location;

    private Integer minExperience;

    private Integer maxExperience;

    private double minSalary;

    private double maxSalary;

    private String jobDescription;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}
