package org.devellopement.pfeback.controller;


import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.services.CoachServiceImpl;
import org.devellopement.pfeback.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CoachController {

    @Autowired
    CoachServiceImpl coachService;


    @GetMapping("/getCoach")
    @ResponseBody
    public List<Coach> getAllCoach() {
        List<Coach> list = coachService.RetreiveAllCoach();
        return list;
    }
    @DeleteMapping("/remove-coach/{coach-id}")
    @ResponseBody
    public void removeCoach(@PathVariable("coach-id") Long id) {
        coachService.deleteCoach(id);
    }
    @GetMapping("/getCoach/{coach-id}")
    @ResponseBody
    public Coach getCoach(@PathVariable("coach-id")Long id)
    {
        return coachService.findById(id);
    }
    @PostMapping("/Add-Coach")
    @ResponseBody
    public Coach addCoach(@RequestBody Coach coach){

        return coachService.addCoach(coach);
    }
    @PutMapping("/{caoch-id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable("coach-id") Long id, @RequestBody Coach modifiedCoach) {
        Coach updatedCoach = coachService.updateCoach(modifiedCoach, id);
        return ResponseEntity.ok(updatedCoach);
    }
}
