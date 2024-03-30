package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Defi;
import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.repository.DefiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefiServiceImpl implements DefiService {
@Autowired
    DefiRepository defiRepository;
    @Override
    public List<Defi> RetreiveAllDefi() {
        return defiRepository.findAll();
    }

    @Override
    public Defi addDefi(Defi defi) {
        return defiRepository.save(defi);
    }

    @Override
    public void deleteDefi(Long id) {
defiRepository.deleteById(id);
    }

    @Override
    public Defi findById(Long id) {
return defiRepository.findById(id).get();    }

    @Override
    public Defi updateDefi(Defi defi, Long id) {
        defi.setId(id);
        return defiRepository.save(defi);
    }
}
