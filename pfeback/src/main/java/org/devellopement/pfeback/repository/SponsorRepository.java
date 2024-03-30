package org.devellopement.pfeback.repository;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor,Long> {
    List<Sponsor> findByUserId(Long userId);
    Sponsor findByUser(User user);

}
