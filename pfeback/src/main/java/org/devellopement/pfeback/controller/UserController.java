package org.devellopement.pfeback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devellopement.pfeback.entities.*;
import org.devellopement.pfeback.repository.*;
import org.devellopement.pfeback.services.FileStorageService;
import org.devellopement.pfeback.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    PasswordEncoder encoder;

    /*@GetMapping("/retrieve-all-user")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> list = userService.RetreiveAllUser();
        return list;
    }*/
    @DeleteMapping("/remove-user/{user-id}")
    @ResponseBody
    public void removeUser(@PathVariable("user-id") Long id) {
        userService.DeleteUser(id);

    }
    @GetMapping("/retrieve-user/{user-id}")
    @ResponseBody
    public User getUserById(@PathVariable("user-id")Long id){
        return userService.findById(id);
    }




    @PostMapping("/Add-user")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Object> requestBody) {
        // Extract user and role data from the request body
        Map<String, Object> userMap = (Map<String, Object>) requestBody.get("user");
        String roleName = (String) requestBody.get("role");

        // Create and save the User entity
        User user = new User();
        user.setUsername((String) userMap.get("username"));
        user.setPassword(encoder.encode((CharSequence) userMap.get("password")));
        user.setEmail((String) userMap.get("email"));
        user.setEnable(Boolean.parseBoolean((String) userMap.get("enable")));
        userRepository.save(user);

        // Find the role by name
        Optional<Role> roleOptional = roleRepository.findByName(roleName);

        // If role doesn't exist, create and save it
        Role role;
        if (roleOptional.isPresent()) {
            role = roleOptional.get();
        } else {
            role = new Role(roleName);
            roleRepository.save(role);
        }
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);
        userRepository.save(user);

        // Check if the role is "player" and create a Player entity
        if ("player".equals(roleName)) {
            Player player = new Player();
            player.setUser(user);
            playerRepository.save(player);
        } else if ("coach".equals(roleName)) {
            Coach coach = new Coach();
            coach.setUser(user);
            coachRepository.save(coach);
        } else if ("sponsor".equals(roleName)) {
            Sponsor sponsor = new Sponsor();
            sponsor.setUser(user);
            sponsorRepository.save(sponsor);
        } else if ("manager".equals(roleName)) {
            Manager manager = new Manager();
            manager.setUser(user);
            managerRepository.save(manager);
        }

        // Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", "User added successfully");
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/find-by-username/{username}")
    @ResponseBody
    public User findByname(@PathVariable("username")String username){
        return userService.findByName(username);
    }
    @PutMapping(value="/modifyuser/{user-id}")
    public User modify(@PathVariable (name="user-id") Long id, @RequestBody  Map<String, Object> requestBody) throws MessagingException {

        return userService.updateUser(requestBody, id);
    }
    @GetMapping("/retrieve-username/{id}")
    @ResponseBody
    public User getusername(@PathVariable("id") String username) {

        return userService.getById(username);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<Map<String, Object>> usersData = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());
            userData.put("enable", user.isEnable());
            userData.put("roles", user.getRoles());
            userData.put("password", user.getPassword());

            Player player = playerRepository.findByUser(user);
            if (player != null) {
                Map<String, Object> playerData = new HashMap<>();
                playerData.put("fullName", player.getFullName());
                playerData.put("dateOfBirth", player.getDateOfBirth());
                playerData.put("mailAddress", player.getMailAddress());
                playerData.put("discordId", player.getDiscordId());
                playerData.put("whatsappPhoneNumber", player.getWhatsappPhoneNumber());
                playerData.put("inGameName", player.getInGameName());
                playerData.put("salary", player.getSalary());
                playerData.put("contractStart", player.getContractStart());
                playerData.put("contractEnd", player.getContractEnd());
                playerData.put("countryOfResidence", player.getCountryOfResidence());
                playerData.put("jerseySize", player.getJerseySize());
                playerData.put("socialMediaLinkFollowers", player.getSocialMediaLinkFollowers());
                // Ajoutez d'autres champs du joueur au besoin
                userData.put("player", playerData);
            }

            Coach coach = coachRepository.findByUser(user);
            if (coach != null) {
                Map<String, Object> coachData = new HashMap<>();
                coachData.put("rapport", coach.getRapport());
                userData.put("coach", coachData);
            }

            // Check if the user has associated sponsor data
            Sponsor sponsor = sponsorRepository.findByUser(user);
            if (sponsor != null) {
                Map<String, Object> sponsorData = new HashMap<>();
                sponsorData.put("detailsContractuel", sponsor.getDetailsContractuel());
                sponsorData.put("termesFinancieres", sponsor.getTermesFinancieres());
                userData.put("sponsor", sponsorData);
            }
            Manager manager = managerRepository.findByUser(user);
            if (manager != null) {
                Map<String, Object> managerData = new HashMap<>();
                managerData.put("nameManager", manager.getNameManager());
                managerData.put("dateOfBirth", manager.getDateOfBirth());
                userData.put("manager", managerData);
            }

            usersData.add(userData);
        }

        return new ResponseEntity<>(usersData, HttpStatus.OK);
    }


}
