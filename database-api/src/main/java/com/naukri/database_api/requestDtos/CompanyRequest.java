package com.naukri.database_api.requestDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyRequest {
    private String name;
    private String email;
    private String websiteLink;
    private String description;
    private int companySize;
    private String industry;
}
