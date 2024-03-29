package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Player;

import java.util.List;

public interface CoachService {
    List<Coach> RetreiveAllCoach ();
    Coach addCoach (Coach coach);
    public void deleteCoach(Long id);
    Coach findById (Long id);
    Coach updateCoach( Coach coach, Long id);
}
