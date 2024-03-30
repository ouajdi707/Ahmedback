package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByUserId(Long userId);
    Player findByUser(User user);

}
