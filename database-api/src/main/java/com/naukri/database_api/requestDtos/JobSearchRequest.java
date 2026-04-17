package com.naukri.database_api.requestDtos;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchRequest {

    private List<String> skills;
    private String location;
    private Integer minExperience;
    private Integer maxExperience;
    private double minSalary;
    private double maxSalary;
}
