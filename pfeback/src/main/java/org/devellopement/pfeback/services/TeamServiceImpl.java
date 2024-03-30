package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.AchivementsTeam;
import org.devellopement.pfeback.entities.Club;
import org.devellopement.pfeback.entities.Team;
import org.devellopement.pfeback.repository.ClubRepository;
import org.devellopement.pfeback.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    TeamRepo teamRepo;
    @Autowired
    ClubRepository clubRepository;

    @Override
    public List<Team> RetreiveAllTeam() {
return  teamRepo.findAll();   }

    @Override
    public Team addTeam(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);

    }

    @Override
    public Team findById(Long id) {
        return teamRepo.findById(id).get();
    }

    @Override
    public Team updateTeam(Team team, Long id) {
        team.setId(id);

        return teamRepo.save(team);
    }
    public void assignTeamToClub(Long teamId, Long clubId) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));

        team.setClub(club);
        teamRepo.save(team);
    }


}
