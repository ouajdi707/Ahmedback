package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.*;
import org.devellopement.pfeback.services.SeanceEntrainementService;
import org.devellopement.pfeback.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class SeanceEntrainementController {
    @Autowired
    SeanceEntrainementService seanceEntrainementService;
    @GetMapping("/getSeanceEntrainement")
    @ResponseBody
    public List<SeanceEntrainement> getAllTournament() {
        List<SeanceEntrainement> list = seanceEntrainementService.RetreiveAllSeanceEntrainement();
        return list;
    }
    @DeleteMapping("/remove-SeanceEntrainement/{SeanceEntrainement-id}")
    @ResponseBody
    public void removeTournamanet(@PathVariable("SeanceEntrainement-id") Long id) {
        seanceEntrainementService.deleteSeanceEntrainement(id);
    }
    @GetMapping("/getSeanceEntrainement/{SeanceEntrainement-id}")
    @ResponseBody
    public SeanceEntrainement getSeanceEntrainement(@PathVariable("SeanceEntrainement-id")Long id)
    {
        return seanceEntrainementService.findById(id);
    }
    @PostMapping("/Add-SeanceEntrainement")
    @ResponseBody
    public SeanceEntrainement addSeanceEntrainement(@RequestBody SeanceEntrainement seanceEntrainement){

        return seanceEntrainementService.addSeanceEntrainement(seanceEntrainement);
    }
    @PutMapping(value="/seance/{SeanceEntrainement-id}")
    public SeanceEntrainement modify(@PathVariable (name="SeanceEntrainement-id") Long id, @RequestBody SeanceEntrainement seanceEntrainement){

        return seanceEntrainementService.updateSeanceEntrainement(seanceEntrainement, id);

    }




}
