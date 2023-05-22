package pl.dmcs.project_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String indexNumber;

    @OneToMany
    private List<Grade> grades;

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
