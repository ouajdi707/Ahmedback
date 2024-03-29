package org.devellopement.pfeback.controller;


import org.devellopement.pfeback.entities.*;
import org.devellopement.pfeback.jwt.JwtUtils;
import org.devellopement.pfeback.payload.JwtResponse;
import org.devellopement.pfeback.payload.LoginRequest;
import org.devellopement.pfeback.payload.MessageResponse;
import org.devellopement.pfeback.payload.SignupRequest;
import org.devellopement.pfeback.repository.*;
import org.devellopement.pfeback.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
            } catch (BadCredentialsException e) {
                // Gérer l'erreur lorsque les informations d'identification sont incorrectes
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Erreur d'authentification : identifiant ou mot de passe incorrect."));
            } catch (AuthenticationException e) {
                // Gérer d'autres erreurs d'authentification
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Erreur d'authentification : " + e.getMessage()));
            }
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom d'utilisateur est déjà pris !"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : l'e-mail est déjà utilisé !"));
        }

        // Créer un nouvel utilisateur en utilisant les champs de la demande de signature
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail());

        // Définir le champ enable
        user.setEnable(signUpRequest.isEnable());

        // Assigner les rôles de l'utilisateur
        List<String> strRoles = signUpRequest.getRole();
        List<Role> roles = new ArrayList<>();
        if (strRoles != null && !strRoles.isEmpty()) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "player":
                        Role playerRole = roleRepository.findByName("ROLE_PLAYER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(playerRole);
                        break;
                    case "coach":
                        Role coachRole = roleRepository.findByName("ROLE_COACH")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(coachRole);
                    case "manager":
                        Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(managerRole);
                    case "sponsor":
                        Role spnsorRole = roleRepository.findByName("ROLE_SPONSOR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(spnsorRole);
                    default:

                        Role userRole = roleRepository.findByName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        } else {
            // Si aucun rôle spécifié, attribuer le rôle par défaut "ROLE_USER"
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        // Assigner les rôles à l'utilisateur
        user.setRoles(roles);

        // Sauvegarder l'utilisateur dans la base de données
        userRepository.save(user);
        if (strRoles.contains("player")) {
            Player player = new Player();
            player.setUser(user);
            playerRepository.save(player);
        }
        if (strRoles.contains("coach")) {
            Coach coach = new Coach();
            coach.setUser(user);
            coachRepository.save(coach);
        }
        if (strRoles.contains("sponsor")) {
            Sponsor sponsor = new Sponsor();
            sponsor.setUser(user);
            sponsorRepository.save(sponsor);
        }
        if (strRoles.contains("manager")) {
            Manager manager = new Manager();
            manager.setUser(user);
            managerRepository.save(manager);
        }



        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }

}
