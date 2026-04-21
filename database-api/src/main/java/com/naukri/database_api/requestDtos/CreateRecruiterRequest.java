package com.naukri.database_api.requestDtos;

import com.naukri.database_api.enums.UserRole;
import com.naukri.database_api.models.Profile;
import lombok.*;


@Getter
@Setter
@ToString
//@AllArgsConstructor
//@NoArgsConstructor
public class CreateRecruiterRequest {

    private String name;
    private String email;
    private String password;
}
