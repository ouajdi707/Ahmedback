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
public class SeanceEntrainement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String FeedbackEntraineurs;
    private String Objectifs;
    private Date dateStart;
    private Date dateEnd;
    @ElementCollection
    private List<String> rapport;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


}
