package com.naukri.database_api.requestDtos;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JobApplicationRequest {

    private Long jobId;
    private Long phoneNumber;
}
