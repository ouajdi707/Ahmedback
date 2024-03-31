package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.entities.User;
import org.devellopement.pfeback.repository.ContratPlayerRepository;
import org.devellopement.pfeback.repository.PlayerRepository;
import org.devellopement.pfeback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    private ContratPlayerRepository contratPlayerRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Player> RetreivePlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
       playerRepository.deleteById(id);
    }

    @Override
    public Player findById(Long id) {
        User user = userRepository.findById(id).get();
        return playerRepository.findByUser(user);
    }

    public Player updatePlayer(Player modifiedPlayer, Long id) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        existingPlayer.setFullName(modifiedPlayer.getFullName());
        existingPlayer.setDateOfBirth(modifiedPlayer.getDateOfBirth());
        existingPlayer.setMailAddress(modifiedPlayer.getMailAddress());
        existingPlayer.setDiscordId(modifiedPlayer.getDiscordId());
        existingPlayer.setWhatsappPhoneNumber(modifiedPlayer.getWhatsappPhoneNumber());
        existingPlayer.setInGameName(modifiedPlayer.getInGameName());
        existingPlayer.setSalary(modifiedPlayer.getSalary());
        existingPlayer.setContractStart(modifiedPlayer.getContractStart());
        existingPlayer.setContractEnd(modifiedPlayer.getContractEnd());
        existingPlayer.setCountryOfResidence(modifiedPlayer.getCountryOfResidence());
        existingPlayer.setJerseySize(modifiedPlayer.getJerseySize());
        existingPlayer.setSocialMediaLinkFollowers(modifiedPlayer.getSocialMediaLinkFollowers());

        return playerRepository.save(existingPlayer);
    }



}
