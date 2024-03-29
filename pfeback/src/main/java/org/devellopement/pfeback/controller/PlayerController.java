package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Player;
import org.devellopement.pfeback.services.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerService;
    @GetMapping("/getPlayer")
    @ResponseBody
    public List<Player> getAllPlayer() {
        List<Player> list = playerService.RetreivePlayer();
        return list;
    }
    @DeleteMapping("/remove-player/{player-id}")
    @ResponseBody
    public void removePlayer(@PathVariable("player-id") Long id) {
        playerService.deletePlayer(id);
    }
    @GetMapping("/getPlayer/{player-id}")
    @ResponseBody
    public Player getPlayer(@PathVariable("player-id")Long id)
    {
        return playerService.findById(id);
    }
    @PostMapping("/Add-player")
    @ResponseBody
    public Player addPlayer(@RequestBody Player player){

        return playerService.addPlayer(player);
    }
    @PutMapping("/{player-id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("player-id") Long id, @RequestBody Player modifiedPlayer) {
        Player updatedPlayer = playerService.updatePlayer(modifiedPlayer, id);
        return ResponseEntity.ok(updatedPlayer);
    }
}
