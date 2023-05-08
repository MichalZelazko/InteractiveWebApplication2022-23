package pl.dmcs.iwamzelazko.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.iwamzelazko.model.Student;
import pl.dmcs.iwamzelazko.model.Team;
import pl.dmcs.iwamzelazko.repository.StudentRepository;
import pl.dmcs.iwamzelazko.repository.TeamRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/teams")
public class TeamRESTController {
    private TeamRepository teamRepository;
    private StudentRepository studentRepository;

    @Autowired
    public TeamRESTController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Team> findTeam(@PathVariable("id") long id){
        Team team = teamRepository.findById(id);
        if(team == null){
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        teamRepository.save(team);
        return new ResponseEntity<Team>(team, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") long id){
        Team team = teamRepository.findById(id);
        if(team == null){
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
//        detachStudentsFromTeam(team);
        teamRepository.delete(team);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteAllTeams(){
//        for (Team team : teamRepository.findAll()) {
//            detachStudentsFromTeam(team);
//        }
        teamRepository.deleteAll();
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Team> updateTeam(@RequestBody Team team, @PathVariable("id") long id){
        team.setId(id);
        teamRepository.save(team);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Team> updateAllTeams(@RequestBody List<Team> teams){
//        for (Team team : teams) {
//            detachStudentsFromTeam(team);
//        }
        teamRepository.deleteAll(teams);
        teamRepository.saveAll(teams);
        return new ResponseEntity<Team>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Team> updatePartOfTeam(Map<String, Object> updates, @PathVariable("id") long id){
        Team team = teamRepository.findById(id);
        if(team == null){
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        if(updates.containsKey("teamName")){
            team.setTeamName((String) updates.get("teamName"));
        }
        teamRepository.save(team);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

//    private void detachStudentsFromTeam(Team team){
//        for (Student student : studentRepository.findAll()) {
//            student.getTeamList().remove(team);
//        }
//    }
}
