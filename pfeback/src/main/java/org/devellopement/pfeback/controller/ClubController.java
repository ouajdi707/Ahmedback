package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Club;
import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.services.ClubServiceImpl;
import org.devellopement.pfeback.services.CoachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ClubController {
    @Autowired
    ClubServiceImpl clubService;


    @GetMapping("/getClub")
    @ResponseBody
    public List<Club> getAllClub() {
        List<Club> list = clubService.RetreiveAllClub();
        return list;
    }
    @DeleteMapping("/remove-club/{club-id}")
    @ResponseBody
    public void removeClub(@PathVariable("club-id") Long id) {
        clubService.deleteClub(id);
    }
    @GetMapping("/getClub/{club-id}")
    @ResponseBody
    public Club getClub(@PathVariable("club-id")Long id)
    {
        return clubService.findById(id);
    }
    @PostMapping("/Add-club")
    @ResponseBody
    public Club addClub(@RequestBody Club club){

        return clubService.addClub(club);
    }
    @PutMapping("/club/{club-id}")
    public ResponseEntity<Club> updateClub(@PathVariable("club-id") Long id, @RequestBody Club modifiedClub) {
        Club updatedClub = clubService.updateClub(modifiedClub, id);
        return ResponseEntity.ok(updatedClub);
    }
}
