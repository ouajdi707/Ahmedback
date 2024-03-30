package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.AchivementsTeam;
import org.devellopement.pfeback.entities.Team;
import org.devellopement.pfeback.repository.AchivementsTeamRepo;
import org.devellopement.pfeback.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AchivementsTeamServiceImpl implements AchivementsTeamService{
@Autowired
    AchivementsTeamRepo achivementsTeamRepo;
@Autowired
    TeamRepo teamRepo;

    @Override
    public List<AchivementsTeam> RetreiveAllAchivementsTeam() {
        return achivementsTeamRepo.findAll();
    }

    @Override
    public AchivementsTeam addAchivementsTeam(AchivementsTeam achivementsTeam) {
        return achivementsTeamRepo.save(achivementsTeam);
    }

    @Override
    public void deleteAchivementsTeam(Long id) {
        achivementsTeamRepo.deleteById(id);

    }

    @Override
    public AchivementsTeam findById(Long id) {
        return achivementsTeamRepo.findById(id).get();
    }

    @Override
    public AchivementsTeam updateAchivementTeam(AchivementsTeam achivementsTeam, Long id) {
        achivementsTeam.setId(id);
        return achivementsTeamRepo.save(achivementsTeam);
    }
    public void assignAchievementsToTeam(Long teamId, List<AchivementsTeam> achievements) {
        Team team = teamRepo.findById(teamId)

                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        for (AchivementsTeam achievement : achievements) {
            achievement.setTeam(team);
            achivementsTeamRepo.save(achievement);
        }
    }
}
