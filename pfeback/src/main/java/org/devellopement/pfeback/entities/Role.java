package org.devellopement.pfeback.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_Id")
    private Long id;

    private String name;
    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }


}

