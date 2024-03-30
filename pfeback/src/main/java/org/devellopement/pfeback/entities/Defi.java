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

public class Defi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name ;
    private String Description;
    private Date dateStart;
    private Date dateEnd;

    @ElementCollection
    private List<String> Event;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

}


