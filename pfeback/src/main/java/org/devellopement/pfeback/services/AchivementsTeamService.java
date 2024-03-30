package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.AchivementsTeam;
import org.devellopement.pfeback.entities.Coach;

import java.util.List;

public interface AchivementsTeamService {

    List<AchivementsTeam> RetreiveAllAchivementsTeam ();
    AchivementsTeam addAchivementsTeam (AchivementsTeam achivementsTeam);
    public void deleteAchivementsTeam(Long id);
    AchivementsTeam findById (Long id);
    AchivementsTeam updateAchivementTeam( AchivementsTeam achivementsTeam, Long id);


}
