package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.entities.Player;

import java.util.List;

public interface ContratPlayerService {


    List<Contratplayer> RetreiveContratPlayer ();
    Contratplayer addContratPlayer (Contratplayer contratplayer);
    public void deleteContratPlayer(Long id);
    Contratplayer findById (Long id);
    Contratplayer updateContratPlayer( Contratplayer contratplayer, Long id);

}
