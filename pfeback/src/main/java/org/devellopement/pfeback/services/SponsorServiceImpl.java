package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.SponsorTeam;
import org.devellopement.pfeback.entities.User;
import org.devellopement.pfeback.repository.SponsorRepository;
import org.devellopement.pfeback.repository.SponsorTeamRepo;
import org.devellopement.pfeback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService{
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private SponsorTeamRepo sponsorTeamRepository;
    @Override
    public List<Sponsor> RetreiveAllSponsor() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void deleteSponsor(Long id) {
        sponsorRepository.deleteById(id);

    }

    @Override
    public Sponsor findById(Long id) {
        User user = userRepository.findById(id).get();
        return sponsorRepository.findByUser(user) ;
    }

    @Override
    public Sponsor updateSponsor(Sponsor modifiedSponsor, Long id) {

        Sponsor existingSponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sponsor not found"));

        existingSponsor.setDetailsContractuel(modifiedSponsor.getDetailsContractuel());
        existingSponsor.setTermesFinancieres(modifiedSponsor.getTermesFinancieres());

        modifiedSponsor.setUser(existingSponsor.getUser());

        return sponsorRepository.save(existingSponsor);
    }
    public void assignSponsorToSponsorTeam(Long sponsorId, Long sponsorTeamId) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new IllegalArgumentException("Sponsor not found"));

        SponsorTeam sponsorTeam = sponsorTeamRepository.findById(sponsorTeamId)
                .orElseThrow(() -> new IllegalArgumentException("Sponsor Team not found"));

        sponsor.setSponsorTeam(sponsorTeam);
        sponsorRepository.save(sponsor);
    }
}
