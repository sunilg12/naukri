package com.naukri.database_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailsServiceRepository extends JpaRepository<User, Long> {

    Optional<UserDetails> loadByUserName(String name);
}
