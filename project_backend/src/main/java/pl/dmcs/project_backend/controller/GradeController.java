package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.repository.GradeRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/grades")
public class GradeController {
    private GradeRepository gradeRepository;

    @Autowired
    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Grade> findGrade(@PathVariable("id") long id){
        Grade grade = gradeRepository.findById(id);
        if (grade == null){
            System.out.println("Grade with id " + id + " not found");
            return new ResponseEntity<Grade>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Grade>(grade, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade){
        gradeRepository.save(grade);
        return new ResponseEntity<Grade>(grade, HttpStatus.CREATED);
    }
}
