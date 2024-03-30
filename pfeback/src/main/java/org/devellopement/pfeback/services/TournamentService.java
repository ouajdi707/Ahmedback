package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.Tournament;

import java.util.List;

public interface TournamentService {
    List<Tournament> RetreiveAllTournament ();
    Tournament addTournament (Tournament tournament);
    public void deleteTournament(Long id);
    Tournament findById (Long id);
    Tournament updateTournament( Tournament tournament, Long id);

}
