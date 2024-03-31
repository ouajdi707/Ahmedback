package org.devellopement.pfeback.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"code"})})

public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_Id")
    private Long id;
    @Column(name = "code")
    private Long code;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name="enable")
    private boolean enable;
    @Column(name="verify")
    private boolean verify;

    @OneToOne( optional = true, cascade = CascadeType.ALL)
    private FileDB image;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_Id") },
            inverseJoinColumns = { @JoinColumn(name = "role_Id") })
    private List<Role> roles;
    @OneToOne(mappedBy = "user")
    private Manager manager;

    @OneToOne(mappedBy = "user")
    private Sponsor sponsor;
    @OneToOne(mappedBy = "user")
    private Coach coach;
    @OneToOne(mappedBy = "user")
    private Player player;


    public User(String username, String password, String email) {
        this.username=username;
                this.password=password;
                this.email=email;
    }
    @PrePersist
    public void generateCode() {
        // Generate a random code here
        // Example: Generating a random long code
        this.code = (long) (Math.random() * 1000000);
    }
}
