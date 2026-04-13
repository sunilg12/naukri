package com.naukri.database_api.requestDtos;

import com.naukri.database_api.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
}
