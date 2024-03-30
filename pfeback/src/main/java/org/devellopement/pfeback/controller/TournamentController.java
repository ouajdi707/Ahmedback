package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Tournament;
import org.devellopement.pfeback.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TournamentController {
    @Autowired
    TournamentServiceImpl tournamentService;
    @GetMapping("/getTournament")
    @ResponseBody
    public List<Tournament> getAllTournament() {
        List<Tournament> list = tournamentService.RetreiveAllTournament();
        return list;
    }
    @DeleteMapping("/remove-tournament/{tournament-id}")
    @ResponseBody
    public void removeTournamanet(@PathVariable("tournament-id") Long id) {
        tournamentService.deleteTournament(id);
    }
    @GetMapping("/getTournament/{tournament-id}")
    @ResponseBody
    public Tournament getTournament(@PathVariable("tournament-id")Long id)
    {
        return tournamentService.findById(id);
    }
    @PostMapping("/Add-Tournament")
    @ResponseBody
    public Tournament addTournament(@RequestBody Tournament tournament){

        return tournamentService.addTournament(tournament);
    }
    @PutMapping("/tournament/{tournament-id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable("sponsor-id") Long id, @RequestBody Tournament modifiedTournament) {
        Tournament updatedTournament = tournamentService.updateTournament(modifiedTournament, id);
        return ResponseEntity.ok(updatedTournament);
    }

}
