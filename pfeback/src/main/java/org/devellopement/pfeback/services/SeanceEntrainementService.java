package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.SeanceEntrainement;
import org.devellopement.pfeback.entities.Sponsor;

import java.util.List;

public interface SeanceEntrainementService {
    List<SeanceEntrainement> RetreiveAllSeanceEntrainement ();
    SeanceEntrainement addSeanceEntrainement (SeanceEntrainement seanceEntrainement);
    public void deleteSeanceEntrainement(Long id);
    SeanceEntrainement findById (Long id);
    SeanceEntrainement updateSeanceEntrainement( SeanceEntrainement seanceEntrainement, Long id);

}
