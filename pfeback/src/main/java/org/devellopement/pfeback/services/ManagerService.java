package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> RetreiveAllManager ();
    Manager addManager (Manager manager);
    public void deleteManager(Long id);
    Manager findById (Long id);
    Manager updateManager( Manager manager, Long id);
}
