package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
}
