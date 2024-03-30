package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.Team;
import org.devellopement.pfeback.entities.Tournament;
import org.devellopement.pfeback.repository.SponsorRepository;
import org.devellopement.pfeback.repository.TeamRepo;
import org.devellopement.pfeback.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    TeamRepo teamRepo;


    @Override
    public List<Tournament> RetreiveAllTournament() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public void deleteTournament(Long id) {
tournamentRepository.deleteById(id);
    }

    @Override
    public Tournament findById(Long id) {
        return tournamentRepository.findById(id).get();
    }

    @Override
    public Tournament updateTournament(Tournament tournament, Long id) {
        tournament.setId(id);
        return tournamentRepository.save(tournament);
    }

    public void assignTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        tournament.getTeams().add(team);
        tournamentRepository.save(tournament);
    }
}
