package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
