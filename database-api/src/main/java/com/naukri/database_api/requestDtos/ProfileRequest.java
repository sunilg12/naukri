package com.naukri.database_api.requestDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProfileRequest {

    String name;
    Integer age;
    List<String> skills;
    String bio;
}
