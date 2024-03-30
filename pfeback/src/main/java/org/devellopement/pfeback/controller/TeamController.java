package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.AchivementsTeam;
import org.devellopement.pfeback.entities.Team;
import org.devellopement.pfeback.entities.Tournament;
import org.devellopement.pfeback.services.ClubServiceImpl;
import org.devellopement.pfeback.services.TeamServiceImpl;
import org.devellopement.pfeback.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class TeamController {
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    ClubServiceImpl clubService;
    @GetMapping("/getTeam")
    @ResponseBody
    public List<Team> getAllTeam() {
        List<Team> list = teamService.RetreiveAllTeam();
        return list;
    }
    @DeleteMapping("/remove-team/{team-id}")
    @ResponseBody
    public void removeTeam(@PathVariable("team-id") Long id) {
        teamService.deleteTeam(id);
    }
    @GetMapping("/getTeam/{team-id}")
    @ResponseBody
    public Team getTeam(@PathVariable("team-id")Long id)
    {
        return teamService.findById(id);
    }
    @PostMapping("/Add-Team")
    @ResponseBody
    public Team addTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }
    @PutMapping("/team/{team-id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("team-id") Long id, @RequestBody Team modifiedTeam) {
        Team updatedTeam =teamService.updateTeam(modifiedTeam, id);
        return ResponseEntity.ok(updatedTeam);
    }
    @PutMapping("/assign-team-to-club/{teamId}/{clubId}")
    public ResponseEntity<String> assignTeamToClub(@PathVariable Long teamId, @PathVariable Long clubId) {
        try {
            teamService.assignTeamToClub(teamId, clubId);
            return ResponseEntity.ok("Team assigned to club successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
