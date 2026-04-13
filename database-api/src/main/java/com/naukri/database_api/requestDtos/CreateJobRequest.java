package com.naukri.database_api.requestDtos;

import com.naukri.database_api.models.Skill;
import com.naukri.database_api.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateJobRequest {

    private User createdBy;
    private List<Skill> skills;
    private String location;
    private String jobDescription;
    private Integer minExperience;
    private Integer maxExperience;
    private double minSalary;
    private double maxSalary;
}
