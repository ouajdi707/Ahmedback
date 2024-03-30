package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    List<Manager> findByUserId(Long userId);
    Manager findByUser(User user);


}
