package pl.dmcs.iwamzelazko.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.iwamzelazko.model.Team;
import pl.dmcs.iwamzelazko.repository.TeamRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamRESTController {
    private TeamRepository teamRepository;

    @Autowired
    public TeamRESTController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") long id){
        Team team = teamRepository.findById(id);
        if(team == null){
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        teamRepository.delete(team);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Team> updateTeam(@RequestBody Team team, @PathVariable("id") long id){
        team.setId(id);
        teamRepository.save(team);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
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
}
