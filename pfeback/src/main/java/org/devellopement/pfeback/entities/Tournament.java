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
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NameTournament ;
    private String Format;
    private Double PrizePool;
    private boolean status;
    private Date DateStart;
    private Date DateEnd;

    @ManyToMany(mappedBy = "tournaments")
    private List<Team> teams;
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Defi> defis;

}
