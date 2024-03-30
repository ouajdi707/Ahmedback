package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.SeanceEntrainement;
import org.devellopement.pfeback.repository.SeanceEntrainementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeanceEntrainementServiceImpl implements SeanceEntrainementService{
    @Autowired
    SeanceEntrainementRepo seanceEntrainementRepo;
    @Override
    public List<SeanceEntrainement> RetreiveAllSeanceEntrainement() {
        return seanceEntrainementRepo.findAll();
    }

    @Override
    public SeanceEntrainement addSeanceEntrainement(SeanceEntrainement seanceEntrainement) {
        return seanceEntrainementRepo.save(seanceEntrainement);
    }

    @Override
    public void deleteSeanceEntrainement(Long id) {
        seanceEntrainementRepo.deleteById(id);
    }

    @Override
    public SeanceEntrainement findById(Long id) {
        return seanceEntrainementRepo.findById(id).get();
    }

    @Override
    public SeanceEntrainement updateSeanceEntrainement(SeanceEntrainement seanceEntrainement, Long id) {
        seanceEntrainement.setId(id);
       return  seanceEntrainementRepo.save(seanceEntrainement);
    }
}
