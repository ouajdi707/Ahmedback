package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Scrims;
import org.devellopement.pfeback.repository.ScrimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrimsServiceImpl implements ScrimsService {
    @Autowired
    ScrimsRepository scrimsRepository;

    @Override
    public List<Scrims> RetreiveAllScrims() {
        return scrimsRepository.findAll();
    }

    @Override
    public Scrims addScrims(Scrims scrims) {
        return scrimsRepository.save(scrims);
    }

    @Override
    public void deleteScrims(Long id) {
        scrimsRepository.deleteById(id);

    }

    @Override
    public Scrims findById(Long id) {
        return scrimsRepository.findById(id).get();
    }

    @Override
    public Scrims updateScrims(Scrims scrims, Long id) {
        scrims.setIdscrims(id);
        return scrimsRepository.save(scrims);
    }
}
