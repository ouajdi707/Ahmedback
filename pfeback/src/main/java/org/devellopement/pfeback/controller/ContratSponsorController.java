package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.ContratSponsor;
import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.services.ContratPlayerServiceImpl;
import org.devellopement.pfeback.services.ContratSponsorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ContratSponsorController {

    @Autowired
    ContratSponsorServiceImpl contratSponosrService;
    @GetMapping("/getContratSponsor")
    @ResponseBody
    public List<ContratSponsor> getAllContratSponsor() {
        List<ContratSponsor> list = contratSponosrService.RetreiveAllContratSponsor();
        return list;
    }
    @DeleteMapping("/remove-ContratSponsor/{contratsponsor-id}")
    @ResponseBody
    public void removeContratSponsor(@PathVariable("contratsponsor-id") Long id) {
        contratSponosrService.deleteContratSponsor(id);
    }
    @GetMapping("/getContratSponsor/{contratsponsor-id}")
    @ResponseBody
    public ContratSponsor getContratSponsor(@PathVariable("contratsponsor-id")Long id)
    {
        return contratSponosrService.findById(id);
    }
    @PostMapping("/Add-contratSponsor")
    @ResponseBody
    public ContratSponsor addContratSponsor(@RequestBody ContratSponsor contratSponsor){

        return contratSponosrService.addContratSponsor(contratSponsor);
    }
    @PutMapping(value="/modifyContratSponsor/{contratsponsor-id}")
    public ContratSponsor modify(@PathVariable (name="contratsponsor-id") Long id, @RequestBody ContratSponsor contratSponsor){

        return contratSponosrService.updateContratSponsor(contratSponsor, id);

    }
    @PostMapping("/assignContractSponsor")
    public ResponseEntity<String> assignContractSponsorToSponsor(@RequestParam Long sponsorId, @RequestBody ContratSponsor contratSponsor) {
        try {
            contratSponosrService.affecterContratSponsor(sponsorId, contratSponsor);
            return ResponseEntity.ok("Contrat assigned successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sponsor not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sponsor already has a contract");
        }
    }

}
