package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Subject;
import pl.dmcs.project_backend.repository.SubjectRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> findSubject(@PathVariable("id") long id){
        Subject subject = subjectRepository.findById(id);
        if (subject == null){
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return new ResponseEntity<Subject>(subject, HttpStatus.CREATED);
    }

}
