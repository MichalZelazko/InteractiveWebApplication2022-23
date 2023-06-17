package pl.dmcs.project_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
