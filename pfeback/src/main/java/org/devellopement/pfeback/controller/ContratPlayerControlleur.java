package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.services.ContratPlayerService;
import org.devellopement.pfeback.services.ContratPlayerServiceImpl;
import org.devellopement.pfeback.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ContratPlayerControlleur {
    @Autowired
    ContratPlayerServiceImpl contratPlayerService;
    @GetMapping("/getContratPlayer")
    @ResponseBody
    public List<Contratplayer> getAllContratPlayer() {
        List<Contratplayer> list = contratPlayerService.RetreiveContratPlayer();
        return list;
    }
    @DeleteMapping("/remove-player/{contratplayer-id}")
    @ResponseBody
    public void removeContratPlayer(@PathVariable("contratplayer-id") Long id) {
        contratPlayerService.deleteContratPlayer(id);
    }
    @GetMapping("/getContratPlayer/{contratplayer-id}")
    @ResponseBody
    public Contratplayer getContratPlayer(@PathVariable("contratplayer-id")Long id)
    {
        return contratPlayerService.findById(id);
    }
    @PostMapping("/Add-contratplayer")
    @ResponseBody
    public Contratplayer addContratPlayer(@RequestBody Contratplayer contratplayer){

        return contratPlayerService.addContratPlayer(contratplayer);
    }
    @PutMapping(value="/modifyContratPlayer/{contratplayer-id}")
    public Contratplayer modify(@PathVariable (name="contratplayer-id") Long id, @RequestBody Contratplayer contratplayer){

        return contratPlayerService.updateContratPlayer(contratplayer, id);

    }
    @PostMapping("/assignContract")
    public ResponseEntity<String> assignContractToPlayer(@RequestParam Long playerId, @RequestBody Contratplayer contratPlayer) {
        try {
            contratPlayerService.affecterContratPlayer(playerId, contratPlayer);
            return ResponseEntity.ok("Contrat assigned successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already has a contract");
        }
    }

}
