package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
