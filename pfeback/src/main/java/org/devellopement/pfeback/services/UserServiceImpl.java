package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.*;
import org.devellopement.pfeback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements Userservice {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    SponsorRepository sponsorRepository;

    @Autowired
    PlayerRepository playerRepository;
    @Override
    public List<User> RetreiveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User Adduser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void DeleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByName(String username) {
        return null;
    }


    @Override
    public User updateUser(User user, Long id) throws MessagingException {
        user.setId(id);
       User userToUpdate= userRepository.findById(id).get();


        return userRepository.save(user);
    }
    public User updateUser(Map<String, Object> requestBody, Long id) {
        // Extract user and role information from the request body
        Map<String, Object> userMap = (Map<String, Object>) requestBody.get("user");
        String password = (String) userMap.get("password");
        String email = (String) userMap.get("email");
        Boolean enable = (Boolean) userMap.get("enable");
        String username = (String) userMap.get("username");
        String roleName = (String) requestBody.get("role");

        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Update user information
            user.setPassword(password);
            user.setEmail(email);
            user.setEnable(enable != null ? enable : false);
            user.setUsername(username);

            // Find the role by name
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                // Update user's roles
                user.getRoles().clear();
                user.getRoles().add(role);
            }
            if (roleName.contains("ROLE_PLAYER")) {
                Player player = new Player();
                player.setUser(user);
                playerRepository.save(player);
            }
            if (roleName.contains("ROLE_COACH")) {
                Coach coach = new Coach();
                coach.setUser(user);
                coachRepository.save(coach);
            }
            if (roleName.contains("ROLE_SPONSOR")) {
                Sponsor sponsor = new Sponsor();
                sponsor.setUser(user);
                sponsorRepository.save(sponsor);
            }
            if (roleName.contains("ROLE_MANAGER")) {
                Manager manager = new Manager();
                manager.setUser(user);
                managerRepository.save(manager);
            }
            // Save the updated user
            return userRepository.save(user);
        } else {
            // User not found
            return null;
        }
    }
    @Override
    public User getById(String id) {
        return userRepository.findUserByUsername(id);

    }


    }

