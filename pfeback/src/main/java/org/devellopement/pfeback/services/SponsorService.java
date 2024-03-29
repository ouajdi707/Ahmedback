package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Sponsor;

import java.util.List;

public interface SponsorService {
    List<Sponsor> RetreiveAllSponsor ();
    Sponsor addSponsor (Sponsor sponsor);
    public void deleteSponsor(Long id);
    Sponsor findById (Long id);
    Sponsor updateSponsor( Sponsor sponsor, Long id);
}
