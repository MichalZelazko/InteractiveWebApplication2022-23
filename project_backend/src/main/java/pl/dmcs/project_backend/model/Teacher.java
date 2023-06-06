package pl.dmcs.project_backend.model;

import jakarta.persistence.*;

import java.util.Set;

public class Teacher extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    private Set<Subject> subjects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
