package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.model.Student;
import pl.dmcs.project_backend.repository.GradeRepository;
import pl.dmcs.project_backend.repository.StudentRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentRESTController {
    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public StudentRESTController(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student student = studentRepository.findById(id);
        if(student == null){
            System.out.println("Student with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        Student student = studentRepository.findById(id);
        if(student == null){
            System.out.println("Student with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        studentRepository.delete(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping
    public ResponseEntity<Student> deleteAllStudents() {
        studentRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student) {
        student.setId(id);
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudents(@RequestBody List<Student> students) {
        studentRepository.deleteAll();
        studentRepository.saveAll(students);
        return ResponseEntity.ok().build();
    }

    private void partialUpdate(Student student, Map<String, Object> updates) {
        if(updates.containsKey("firstName")) {
            student.setFirstName((String) updates.get("firstName"));
        }
        if(updates.containsKey("lastName")) {
            student.setLastName((String) updates.get("lastName"));
        }
        if(updates.containsKey("pesel")) {
            student.setPesel((String) updates.get("pesel"));
        }
        if(updates.containsKey("grades")) {
            student.setGrades((Set<Grade>) updates.get("grades"));
        }
        studentRepository.save(student);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable("id") long id, @RequestBody Map<String, Object> updates) {
        Student studentToUpdate = studentRepository.findById(id);
        if(studentToUpdate == null){
            System.out.println("Student with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        partialUpdate(studentToUpdate, updates);
        return ResponseEntity.ok(studentToUpdate);
    }

}
