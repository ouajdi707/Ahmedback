package org.devellopement.pfeback.services;

import org.devellopement.pfeback.entities.Role;
import org.devellopement.pfeback.entities.User;
import org.devellopement.pfeback.repository.RoleRepository;
import org.devellopement.pfeback.repository.UserRepository;
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

