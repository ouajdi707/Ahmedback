package org.devellopement.pfeback.controller;


import org.devellopement.pfeback.entities.Coach;
import org.devellopement.pfeback.entities.Manager;
import org.devellopement.pfeback.services.CoachServiceImpl;
import org.devellopement.pfeback.services.MangerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ManagerController {
    @Autowired
    MangerServiceImpl mangerService;


    @GetMapping("/getManager")
    @ResponseBody
    public List<Manager> getAllManager() {
        List<Manager> list = mangerService.RetreiveAllManager();
        return list;
    }
    @DeleteMapping("/remove-manager/{manager-id}")
    @ResponseBody
    public void removeManager(@PathVariable("manager-id") Long id) {
        mangerService.deleteManager(id);
    }
    @GetMapping("/getManager/{manager-id}")
    @ResponseBody
    public Manager getManager(@PathVariable("manager-id")Long id)
    {
        return mangerService.findById(id);
    }
    @PostMapping("/Add-Manager")
    @ResponseBody
    public Manager addManager(@RequestBody Manager manager){

        return mangerService.addManager(manager);
    }
    @PutMapping("/manager/{manager-id}")
    public ResponseEntity<Manager> updateManager(@PathVariable("manager-id") Long id, @RequestBody Manager modifiedManager) {
        Manager updatedManager = mangerService.updateManager(modifiedManager, id);
        return ResponseEntity.ok(updatedManager);
    }
}
