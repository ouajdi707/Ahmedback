package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Scrims;
import org.devellopement.pfeback.entities.Sponsor;
import org.devellopement.pfeback.services.ScrimsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ScrimsController {
    @Autowired
    ScrimsServiceImpl scrimsService;
    @GetMapping("/getScrims")
    @ResponseBody
    public List<Scrims> getAllScrims() {
        List<Scrims> list = scrimsService.RetreiveAllScrims();
        return list;
    }
    @DeleteMapping("/remove-scrims/{scrims-id}")
    @ResponseBody
    public void removeScrims(@PathVariable("scrims-id") Long id) {
        scrimsService.deleteScrims(id);
    }
    @GetMapping("/getScrims/{scrims-id}")
    @ResponseBody
    public Scrims getScrims(@PathVariable("scrims-id")Long id)
    {
        return scrimsService.findById(id);
    }
    @PostMapping("/Add-scrims")
    @ResponseBody
    public Scrims addScrims(@RequestBody Scrims scrims){

        return scrimsService.addScrims(scrims);
    }
    @PutMapping("/scrims/{scrims-id}")
    public ResponseEntity<Scrims> updateScrims(@PathVariable("scrims-id") Long id, @RequestBody Scrims modifiedScrims) {
        Scrims updatedScrims = scrimsService.updateScrims(modifiedScrims, id);
        return ResponseEntity.ok(updatedScrims);
    }
}
