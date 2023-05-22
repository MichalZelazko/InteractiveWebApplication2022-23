package pl.dmcs.project_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher extends Person{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String academicDegree;

        @OneToMany(mappedBy = "teacher")
        private List<Subject> subjects;



        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public List<Subject> getSubjects() {
                return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
                this.subjects = subjects;
        }

        public String getAcademicDegree() {
                return academicDegree;
        }

        public void setAcademicDegree(String academicDegree) {
                this.academicDegree = academicDegree;
        }

}
