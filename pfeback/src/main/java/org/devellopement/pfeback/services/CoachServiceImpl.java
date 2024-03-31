package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoachServiceImpl implements CoachService{
    @Autowired
    CoachRepository coachRepository;
    @Override
    public List<Coach> RetreiveAllCoach() {
        return coachRepository.findAll();   }

    @Override
    public Coach addCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void deleteCoach(Long id) {
coachRepository.deleteById(id);
    }

    @Override
    public Coach findById(Long id) {
       return coachRepository.findById(id).get();
    }

    @Override
    public Coach updateCoach(Coach modifiedCoach, Long id) {
        Coach existingCoach = coachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
        System.out.println(modifiedCoach.getRapport());
        existingCoach.setRapport(modifiedCoach.getRapport());

        modifiedCoach.setUser(existingCoach.getUser());

        return coachRepository.save(existingCoach);
    }
}


