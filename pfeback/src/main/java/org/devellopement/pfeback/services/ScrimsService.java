package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Scrims;
import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.repository.ScrimsRepository;

import java.util.List;

public interface ScrimsService {
    List<Scrims> RetreiveAllScrims ();
    Scrims addScrims (Scrims scrims);
    public void deleteScrims(Long id);
    Scrims findById (Long id);
    Scrims updateScrims( Scrims scrims, Long id);
}
