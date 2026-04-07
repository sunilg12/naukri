package com.naukri.database_api.requestDtos;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.models.Skill;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private List<Skill> skills;
}
