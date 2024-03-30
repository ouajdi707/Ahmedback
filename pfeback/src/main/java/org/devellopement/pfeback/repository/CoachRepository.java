package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    List<Coach> findByUserId(Long userId);
    Coach findByUser(User user);

}
