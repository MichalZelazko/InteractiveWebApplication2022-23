package pl.dmcs.project_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private long id;

    @Min(2)
    @Max(5)
    private int grade;

    @OneToOne
    private Student student;

    @OneToOne
    private Subject subject;
}
