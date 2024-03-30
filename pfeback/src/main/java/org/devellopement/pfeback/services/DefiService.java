package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Defi;
import org.devellopement.pfeback.entities.Manager;

import java.util.List;

public interface DefiService {

    List<Defi> RetreiveAllDefi ();
    Defi addDefi (Defi defi);
    public void deleteDefi(Long id);
    Defi findById (Long id);
    Manager updateDefi( Defi defi, Long id);

}
