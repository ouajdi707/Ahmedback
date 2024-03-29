package org.devellopement.pfeback;

import org.devellopement.pfeback.entities.Role;
import org.devellopement.pfeback.entities.User;
import org.devellopement.pfeback.repository.RoleRepository;
import org.devellopement.pfeback.repository.UserRepository;
import org.devellopement.pfeback.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PfebackApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(PfebackApplication.class, args);
    }


    @Bean
    CommandLineRunner start() {


        return args -> {
            System.out.println("run");
            List<Role> roles = (List<Role>) roleRepository.findAll();
            if (roles.isEmpty()) {
                Role role = new Role();
                role.setName("ROLE_USER");
                roleRepository.save(role);
                Role role2 = new Role();
                role2.setName("ROLE_ADMIN");
                roleRepository.save(role2);
                Role roleCoach = new Role();
                roleCoach.setName("ROLE_COACH");
                roleRepository.save(roleCoach);
                Role rolePlayer = new Role();
                rolePlayer.setName("ROLE_PLAYER");
                roleRepository.save(rolePlayer);
                Role roleSponsor = new Role();
                roleSponsor.setName("ROLE_SPONSOR");
                roleRepository.save(roleSponsor);
                Role roleManager = new Role();
                roleManager.setName("ROLE_MANAGER");
                roleRepository.save(roleManager);


            }
            if (!userRepository.existsByEmail("ahmed@gmail.com")) {
                String bcrypt = webSecurityConfig.passwordEncoder().encode("12345678");
                List<Role> rolesAdmin = new ArrayList<>();
                Role r = roleRepository.findByName("ROLE_ADMIN")
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                rolesAdmin.add(r);
                User user = new User();
                user.setRoles(rolesAdmin);
                user.setUsername("admin");
                user.setPassword(bcrypt);
                user.setEmail("ahmed@gmail.com");
                userRepository.save(user);
            }
        };
    }
}
