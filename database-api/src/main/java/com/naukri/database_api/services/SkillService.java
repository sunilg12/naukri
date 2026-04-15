package com.naukri.database_api.services;

import com.naukri.database_api.models.Skill;
import com.naukri.database_api.repositories.SkillRepository;
import com.naukri.database_api.requestDtos.SkillRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {

    SkillRepository skillRepo;

    SkillService(SkillRepository skillRepo){
        this.skillRepo = skillRepo;
    }

    public Skill saveSkill(SkillRequest request){
        Skill s = new Skill();

        s.setName(request.getName());

        skillRepo.save(s);

        return s;
    }

    public Skill findByName(String name){
        return skillRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Skill Not Found: " + name));
    }
}
