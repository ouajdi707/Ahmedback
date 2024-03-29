package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MangerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Override
    public List<Manager> RetreiveAllManager() {
        return managerRepository.findAll();
    }
    @Override
    public Manager addManager(Manager manager) {
return managerRepository.save(manager);    }

    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id).get();
    }

    @Override
    public Manager updateManager(Manager modifiedManager, Long id) {
        Manager existingManager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manager not found"));

        existingManager.setNameManager(modifiedManager.getNameManager());
        existingManager.setDateOfBirth(modifiedManager.getDateOfBirth());

        modifiedManager.setUser(existingManager.getUser());

        return managerRepository.save(existingManager);
    }
}
