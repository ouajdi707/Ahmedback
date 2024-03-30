package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.SponsorTeam;
import org.devellopement.pfeback.repository.SponsorTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SponsorTeamServiceImpl implements SponsorTeamService {
    @Autowired
    SponsorTeamRepo sponsorTeamRepo;
    @Override
    public List<SponsorTeam> RetreiveAllSponsorTeam() {
        return sponsorTeamRepo.findAll();
    }

    @Override
    public SponsorTeam addSponsorTeam(SponsorTeam sponsorTeam) {
        return sponsorTeamRepo.save(sponsorTeam);
    }

    @Override
    public void deleteSponsorTeam(Long id) {
sponsorTeamRepo.deleteById(id);
    }
    @Override
    public SponsorTeam findById(Long id) {
        return sponsorTeamRepo.findById(id).get();
    }
    @Override
    public SponsorTeam updateSponsorTeam(SponsorTeam sponsorTeam, Long id) {
        sponsorTeam.setId(id);
        return sponsorTeamRepo.save(sponsorTeam);
    }
}
