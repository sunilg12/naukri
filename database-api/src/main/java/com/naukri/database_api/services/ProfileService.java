package com.naukri.database_api.services;

import com.naukri.database_api.models.Profile;
import com.naukri.database_api.models.Skill;
import com.naukri.database_api.repositories.ProfileRepository;
import com.naukri.database_api.requestDtos.ProfileRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    SkillService skillService;
    ProfileRepository profileRepo;

    public Profile saveProfile(ProfileRequest request) {
        Profile userProfile = new Profile();

        userProfile.setName(request.getName());
        userProfile.setAge(request.getAge());
        userProfile.setBio(request.getBio());

        List<Skill> skills = request.getSkills().stream()
                .map(skillService::findByName)
                .toList();

        userProfile.setSkills(userProfile.getSkills());
        profileRepo.save(userProfile);

        return userProfile;
    }

    public Profile updateProfile(ProfileRequest request){
        Profile userProfile = new Profile();

        if(request.getAge() != null){
            userProfile.setAge(request.getAge());
        }

        if(request.getBio() != null){
            userProfile.setBio(request.getBio());
        }

        if(request.getSkills() != null){
            userProfile.setSkills(request.getSkills().stream()
                    .map(skillService::findByName)
                    .toList());
        }

        return profileRepo.save(userProfile);
    }
}
