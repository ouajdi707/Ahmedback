package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Player;

import java.util.List;

public interface PlayerService {

    List<Player> RetreivePlayer ();
    Player addPlayer (Player player);
    public void deletePlayer(Long id);
    Player findById (Long id);
    Player updatePlayer( Player player, Long id);
}
