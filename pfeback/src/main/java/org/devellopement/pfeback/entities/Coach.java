package org.devellopement.pfeback.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coach  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String Rapport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Nom de la colonne dans la table player qui fait référence à l'utilisateur
    private User user;


}
