package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
