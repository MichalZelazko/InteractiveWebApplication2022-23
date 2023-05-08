package pl.dmcs.iwamzelazko.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String teamName;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "teamList")
//    private List<Student> studentslist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

//    public List<Student> getStudentslist() {
//        return studentslist;
//    }
//
//    public void setStudentslist(List<Student> studentslist) {
//        this.studentslist = studentslist;
//    }
}
