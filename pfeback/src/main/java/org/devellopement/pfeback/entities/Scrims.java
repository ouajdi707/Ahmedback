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
public class Scrims {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idscrims;
    private Number IdSession;
    private String Description;
    private String Niveau;
    private String Mode;
}
