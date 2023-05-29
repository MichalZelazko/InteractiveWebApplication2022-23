package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Teacher;
import pl.dmcs.project_backend.repository.TeacherRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/teachers")
public class TeacherRESTController {

    private TeacherRepository teacherRepository;
    @Autowired
    public TeacherRESTController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable long id) {
        Teacher teacher = teacherRepository.findById(id);
        if(teacher == null){
            System.out.println("Teacher with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable long id) {
        Teacher teacher = teacherRepository.findById(id);
        if(teacher == null){
            System.out.println("Teacher with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        teacherRepository.delete(teacher);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping
    public ResponseEntity<Teacher> deleteAllTeachers() {
        teacherRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        teacherRepository.save(teacher);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping
    public ResponseEntity<Teacher> updateTeachers(@RequestBody List<Teacher> teachers) {
        teacherRepository.deleteAll();
        teacherRepository.saveAll(teachers);
        return ResponseEntity.ok().build();
    }
}
