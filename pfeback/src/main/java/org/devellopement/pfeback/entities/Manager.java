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
public class Manager  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NameManager;
    private Date dateOfBirth;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
