package org.devellopement.pfeback.entities;

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
public class AchivementsTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String TournamentName ;
    private String Rank;
    private Date DateAchived;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
