package org.devellopement.pfeback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.devellopement.pfeback.entities.Contratplayer;
import org.devellopement.pfeback.entities.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date dateOfBirth;
    private String mailAddress;
    private Long discordId;
    private Long whatsappPhoneNumber;
    private String inGameName;
    private Double salary;
    private Date contractStart;
    private Date contractEnd;
    private String countryOfResidence;
    private String jerseySize;
    private String socialMediaLinkFollowers;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contratplayer contratplayer;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // Nom de la colonne dans la table player qui fait référence à l'utilisateur
    private User user;

}