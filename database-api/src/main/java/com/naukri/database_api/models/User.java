package com.naukri.database_api.models;

import com.naukri.database_api.enums.UserRole;
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
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userType;

    @ManyToOne
    private Company company;

    @ManyToMany
    private List<Skill> skills;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
