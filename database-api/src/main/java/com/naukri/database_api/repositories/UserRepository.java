package com.naukri.database_api.repositories;

import com.naukri.database_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrName(String email, String name);

    Optional<Object> findByName(String name);

    Optional<Object> findByPhoneNumber(String phoneNo);
}
