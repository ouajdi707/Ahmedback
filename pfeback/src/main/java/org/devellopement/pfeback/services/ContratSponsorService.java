package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.ContratSponsor;
import org.devellopement.pfeback.entities.Tournament;

import java.util.List;

public interface ContratSponsorService {
    List<ContratSponsor> RetreiveAllContratSponsor ();
    ContratSponsor addContratSponsor (ContratSponsor contratSponsor);
    public void deleteContratSponsor(Long id);
    ContratSponsor findById (Long id);
    ContratSponsor updateContratSponsor( ContratSponsor contratSponsor, Long id);
}
