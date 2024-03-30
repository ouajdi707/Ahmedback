package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.*;
import org.devellopement.pfeback.repository.ContratSponsorRepository;
import org.devellopement.pfeback.repository.SponsorRepository;
import org.devellopement.pfeback.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContratSponsorServiceImpl implements ContratSponsorService {

    @Autowired
    ContratSponsorRepository contratSponsorRepository;
    @Autowired
    SponsorRepository sponsorRepository;


    @Override
    public List<ContratSponsor> RetreiveAllContratSponsor() {
        return contratSponsorRepository.findAll();
    }

    @Override
    public ContratSponsor addContratSponsor(ContratSponsor contratSponsor) {
        return contratSponsorRepository.save(contratSponsor);
    }

    @Override
    public void deleteContratSponsor(Long id) {
contratSponsorRepository.deleteById(id);
    }

    @Override
    public ContratSponsor findById(Long id) {
        return contratSponsorRepository.findById(id).get();
    }

    @Override
    public ContratSponsor updateContratSponsor(ContratSponsor contratSponsor, Long id) {

        contratSponsor.setId(id);
        return contratSponsorRepository.save(contratSponsor);
    }
    public void affecterContratSponsor(Long sponsorId, ContratSponsor contratSponsor) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new IllegalArgumentException("Sponsor not found"));
        if (sponsor.getContratSponsor() != null) {
            throw new IllegalStateException("Sponsor already has a contract");
        }
        contratSponsor.setSponsor(sponsor);
        sponsor.setContratSponsor(contratSponsor);
        sponsorRepository.save(sponsor);
    }
}
