package org.devellopement.pfeback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String DetailsContractuel ;
    private String TermesFinancieres;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // Nom de la colonne dans la table player qui fait référence à l'utilisateur

    private User user;
    @OneToOne(mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContratSponsor contratSponsor;
    @ManyToOne
    @JoinColumn(name = "sponsorteam_id")
    private SponsorTeam sponsorTeam;
}
