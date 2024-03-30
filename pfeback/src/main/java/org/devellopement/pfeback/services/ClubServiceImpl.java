package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Club;
import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    ClubRepository clubRepository;
    @Override
    public List<Club> RetreiveAllClub() {
        return clubRepository.findAll();
    }

    @Override
    public Club addClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long id) {
clubRepository.deleteById(id);
    }

    @Override
    public Club findById(Long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public Club updateClub(Club club, Long id) {
        club.setId(id);
        return clubRepository.save(club);
    }
}
