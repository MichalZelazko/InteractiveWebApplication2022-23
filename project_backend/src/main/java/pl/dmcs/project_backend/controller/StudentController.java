package pl.dmcs.project_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.model.Student;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.repository.GradeRepository;
import pl.dmcs.project_backend.repository.StudentRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            System.out.println("Student with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        studentRepository.delete(student);
        return ResponseEntity.ok().body(student);
    }

}
