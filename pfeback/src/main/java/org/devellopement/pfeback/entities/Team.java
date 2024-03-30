package org.devellopement.pfeback.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String TeamName ;
    private String termeFinancier;
    private String Description;
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    @OneToMany(mappedBy = "team")
    private List<AchivementsTeam> achivements;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @OneToMany(mappedBy = "team")
    private List<SeanceEntrainement> seancesEntrainement;


}
