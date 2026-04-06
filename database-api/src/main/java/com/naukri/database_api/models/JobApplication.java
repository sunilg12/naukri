package com.naukri.database_api.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Job job;

    private User applicant;

    private String status;

    private LocalDateTime appliedAt;
}
