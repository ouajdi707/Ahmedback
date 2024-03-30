package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Team;
import org.devellopement.pfeback.entities.Tournament;

import java.util.List;

public interface TeamService {
    List<Team> RetreiveAllTeam ();
    Team addTeam (Team team);
    public void deleteTeam(Long id);
    Team findById (Long id);
    Team updateTeam( Team team, Long id);

}
