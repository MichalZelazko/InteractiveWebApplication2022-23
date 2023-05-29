package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.repository.GradeRepository;

import java.util.List;
import java.util.Map;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/grades")
public class GradeRESTController {

    private GradeRepository GradeRepository;

    @Autowired
    public GradeRESTController(GradeRepository GradeRepository) {
        this.GradeRepository = GradeRepository;
    }
    @GetMapping
    public List<Grade> findAllGrades() {
        return GradeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> findGrade(@PathVariable long id) {
        Grade Grade = GradeRepository.findById(id);
        if(Grade == null){
            System.out.println("Grade with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable long id) {
        Grade Grade = GradeRepository.findById(id);
        if(Grade == null){
            System.out.println("Grade with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        GradeRepository.delete(Grade);
        return ResponseEntity.ok(Grade);
    }

    @DeleteMapping
    public ResponseEntity<Grade> deleteAllGrades() {
        GradeRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade Grade) {
        GradeRepository.save(Grade);
        return ResponseEntity.ok(Grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable long id, @RequestBody Grade grade) {
        grade.setId(id);
        GradeRepository.save(grade);
        return ResponseEntity.ok(grade);
    }

    @PutMapping
    public ResponseEntity<Grade> updateAllGrades(@RequestBody List<Grade> grades) {
        GradeRepository.deleteAll();
        GradeRepository.saveAll(grades);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Grade> patchGrade(@PathVariable long id, @RequestBody Grade grade, Map<String, Object> updates) {
        Grade gradeToUpdate = GradeRepository.findById(id);
        if(gradeToUpdate == null){
            System.out.println("Grade with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        updates.forEach((key, value) -> {
            if (key.equals("grade")) {
                gradeToUpdate.setGrade((int) value);
            }
        });
        GradeRepository.save(gradeToUpdate);
        return ResponseEntity.ok(gradeToUpdate);
    }


}
