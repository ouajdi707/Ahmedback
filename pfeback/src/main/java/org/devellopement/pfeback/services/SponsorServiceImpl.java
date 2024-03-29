package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService{
    @Autowired
    SponsorRepository sponsorRepository;
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
        return sponsorRepository.findById(id).get();
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
}
