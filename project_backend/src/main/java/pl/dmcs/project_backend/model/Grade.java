package pl.dmcs.project_backend.model;

import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(2)
    @Max(5)
    private int grade;

    @ManyToOne
    private Student student;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Subject subject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
