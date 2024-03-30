package org.devellopement.pfeback.controller;

import org.devellopement.pfeback.entities.Defi;
import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.services.DefiServiceImpl;
import org.devellopement.pfeback.services.MangerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class DefiController {
    @Autowired
    DefiServiceImpl defiService;


    @GetMapping("/getDefi")
    @ResponseBody
    public List<Defi> getAllDefi() {
        List<Defi> list = defiService.RetreiveAllDefi();
        return list;
    }
    @DeleteMapping("/remove-defi/{defi-id}")
    @ResponseBody
    public void removeDefi(@PathVariable("defi-id") Long id) {
        defiService.deleteDefi(id);
    }
    @GetMapping("/getDefi/{defi-id}")
    @ResponseBody
    public Defi getDefi(@PathVariable("defi-id")Long id)
    {
        return defiService.findById(id);
    }
    @PostMapping("/Add-Defi")
    @ResponseBody
    public Defi addManager(@RequestBody Defi defi){

        return defiService.addDefi(defi);
    }
    @PutMapping("/defi/{defi-id}")
    public ResponseEntity<Defi> updateDefi(@PathVariable("defi-id") Long id, @RequestBody Defi modifiedDefi) {
        Defi updatedManager = defiService.updateDefi(modifiedDefi, id);
        return ResponseEntity.ok(updatedManager);
    }
}
