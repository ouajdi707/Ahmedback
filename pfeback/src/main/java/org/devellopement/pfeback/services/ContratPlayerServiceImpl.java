package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.repository.ContratPlayerRepository;
import org.devellopement.pfeback.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContratPlayerServiceImpl implements ContratPlayerService{
    @Autowired
    ContratPlayerRepository contratPlayerRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Contratplayer> RetreiveContratPlayer() {
        return  contratPlayerRepository.findAll();
    }

    @Override
    public Contratplayer addContratPlayer(Contratplayer contratplayer) {
        return contratPlayerRepository.save(contratplayer);
    }

    @Override
    public void deleteContratPlayer(Long id) {
contratPlayerRepository.deleteById(id);
    }

    @Override
    public Contratplayer findById(Long id) {
        return contratPlayerRepository.findById(id).get();
    }

    @Override
    public Contratplayer updateContratPlayer(Contratplayer contratplayer, Long id) {
        contratplayer.setId(id);
        return contratPlayerRepository.save(contratplayer);
    }



    public void affecterContratPlayer(Long playerId, Contratplayer contratPlayer) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        if (player.getContratplayer() != null) {
            throw new IllegalStateException("Player already has a contract");
        }
        contratPlayer.setPlayer(player);
        player.setContratplayer(contratPlayer);
        playerRepository.save(player);
    }
}
