package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String password;

    private Long phoneNumber;

    private String userType;

    private Company company;

    private List<Skill> skills;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
