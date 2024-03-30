package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.SponsorTeam;

import java.util.List;

public interface SponsorTeamService {
    List<SponsorTeam> RetreiveAllSponsorTeam ();
    SponsorTeam addSponsorTeam (SponsorTeam sponsorTeam);
    public void deleteSponsorTeam(Long id);
    SponsorTeam findById (Long id);
    SponsorTeam updateSponsorTeam( SponsorTeam sponsorTeam, Long id);


}
