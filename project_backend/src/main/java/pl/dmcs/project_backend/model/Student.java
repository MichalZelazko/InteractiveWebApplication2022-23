package pl.dmcs.project_backend.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Grade> grades;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
