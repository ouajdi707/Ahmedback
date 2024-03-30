package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.AchivementsTeam;
import org.devellopement.pfeback.entities.Club;
import org.devellopement.pfeback.services.AchivementsTeamService;
import org.devellopement.pfeback.services.AchivementsTeamServiceImpl;
import org.devellopement.pfeback.services.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class AchivementTeamController {
    @Autowired
    AchivementsTeamServiceImpl achivementsTeamService;


    @GetMapping("/getAchivementTeam")
    @ResponseBody
    public List<AchivementsTeam> getAllAchivementTeam() {
        List<AchivementsTeam> list = achivementsTeamService.RetreiveAllAchivementsTeam();
        return list;
    }
    @DeleteMapping("/remove-AchivementsTeam/{achivementsTeam-id}")
    @ResponseBody
    public void removeAchivementTeam(@PathVariable("achivementTeam-id") Long id) {
        achivementsTeamService.deleteAchivementsTeam(id);
    }
    @GetMapping("/getAchivementsTeam/{achivementsTeam-id}")
    @ResponseBody
    public AchivementsTeam getAchivementsTeam(@PathVariable("achivementTeam-id")Long id)
    {
        return achivementsTeamService.findById(id);
    }
    @PostMapping("/Add-AchivementsTeam")
    @ResponseBody
    public AchivementsTeam addAchivementsTeam(@RequestBody AchivementsTeam achivementsTeam){

        return achivementsTeamService.addAchivementsTeam(achivementsTeam);
    }
    @PutMapping("/AchivementsTeam/{schivementTeam-id}")
    public ResponseEntity<AchivementsTeam> updateAchivementTeam(@PathVariable("achivementsTeam-id") Long id, @RequestBody AchivementsTeam modifiedAchivementsTeam) {
        AchivementsTeam updatedAchivementTeam = achivementsTeamService.updateAchivementTeam(modifiedAchivementsTeam, id);
        return ResponseEntity.ok(updatedAchivementTeam);
    }
    @PostMapping("/{teamId}/achievements")
    public ResponseEntity<String> addAchievementsToTeam(@PathVariable Long teamId, @RequestBody List<AchivementsTeam> achievements) {
        try {
            achivementsTeamService.assignAchievementsToTeam(teamId, achievements);
            return ResponseEntity.ok("Achievements assigned to team successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
