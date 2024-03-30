package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Club;
import org.devellopement.pfeback.entities.Coach;

import java.util.List;

public interface ClubService {
    List<Club> RetreiveAllClub ();
    Club addClub (Club club);
    public void deleteClub(Long id);
    Club findById (Long id);
    Club updateClub( Club club, Long id);
}
