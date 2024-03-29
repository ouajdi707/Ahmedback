package org.devellopement.pfeback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devellopement.pfeback.entities.FileDB;
import org.devellopement.pfeback.entities.Role;
import org.devellopement.pfeback.entities.User;
import org.devellopement.pfeback.repository.RoleRepository;
import org.devellopement.pfeback.repository.UserRepository;
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
    PasswordEncoder encoder;

    @GetMapping("/retrieve-all-user")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> list = userService.RetreiveAllUser();
        return list;
    }
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
        user.setPassword( encoder.encode((CharSequence) userMap.get("password")));
        user.setEmail((String) userMap.get("email"));
        user.setEnable((boolean) userMap.get("enable"));
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

        // Assign the role to the user
        userRepository.save(user);
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


}
