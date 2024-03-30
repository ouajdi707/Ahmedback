package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.entities.SponsorTeam;
import org.devellopement.pfeback.services.SponsorTeamService;
import org.devellopement.pfeback.services.SponsorTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class SponsorTeamController {

    @Autowired
    SponsorTeamServiceImpl sponsorTeamService;
    @GetMapping("/getSponsorTeam")
    @ResponseBody
    public List<SponsorTeam> getAllSponsorTeam() {
        List<SponsorTeam> list = sponsorTeamService.RetreiveAllSponsorTeam();
        return list;
    }
    @DeleteMapping("/remove-sponsorTeam/{sponsorTeam-id}")
    @ResponseBody
    public void removeSponsorTeam(@PathVariable("sponsorTeam-id") Long id) {
        sponsorTeamService.deleteSponsorTeam(id);
    }
    @GetMapping("/getSponsorTeam/{sponsorTeam-id}")
    @ResponseBody
    public SponsorTeam getSponsorTeam(@PathVariable("sponsorTeam-id")Long id)
    {
        return sponsorTeamService.findById(id);
    }
    @PostMapping("/Add-sponsorTeam")
    @ResponseBody
    public SponsorTeam addSponsorTeam(@RequestBody SponsorTeam sponsorTeam){

        return sponsorTeamService.addSponsorTeam(sponsorTeam);
    }
    @PutMapping("/sponsorTeam/{sponsorTeam-id}")
    public ResponseEntity<SponsorTeam> updateSponsorTeam(@PathVariable("sponsorTeam-id") Long id, @RequestBody SponsorTeam modifiedSponsorTeam) {
        SponsorTeam updatedSponsorTeam = sponsorTeamService.updateSponsorTeam(modifiedSponsorTeam, id);
        return ResponseEntity.ok(updatedSponsorTeam);
    }


}
