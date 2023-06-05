package pl.dmcs.project_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Subject;
import pl.dmcs.project_backend.repository.SubjectRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subjects")
public class SubjectRESTController {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectRESTController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> findSubject(@PathVariable long id) {
        Subject subject = subjectRepository.findById(id);
        if(subject == null){
            System.out.println("Subject with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> addSubject(Subject subject) {
        subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable long id) {
        Subject subject = subjectRepository.findById(id);
        if(subject == null){
            System.out.println("Subject with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        subjectRepository.delete(subject);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping
    public ResponseEntity<Subject> deleteAllSubjects() {
        subjectRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable long id, @RequestBody Subject subject) {
        subject.setId(id);
        subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    @PutMapping
    public ResponseEntity<Subject> updateSubjects(@RequestBody List<Subject> subjects) {
        subjectRepository.deleteAll();
        subjectRepository.saveAll(subjects);
        return ResponseEntity.ok().build();
    }
}
