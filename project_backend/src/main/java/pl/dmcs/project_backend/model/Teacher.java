package pl.dmcs.project_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Teacher extends Person{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String academicDegree;

        @OneToMany(mappedBy = "teacher")
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

        public String getAcademicDegree() {
                return academicDegree;
        }

        public void setAcademicDegree(String academicDegree) {
                this.academicDegree = academicDegree;
        }

}
