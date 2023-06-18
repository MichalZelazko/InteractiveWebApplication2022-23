package pl.dmcs.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.model.Subject;
import pl.dmcs.project_backend.model.Teacher;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.repository.GradeRepository;
import pl.dmcs.project_backend.repository.SubjectRepository;
import pl.dmcs.project_backend.repository.TeacherRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository ) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable("id") long id){
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null){
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        teacherRepository.save(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") long id){
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null){
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Subject> subjects = teacher.getSubjects();
        List<Grade> grades;
        for (Subject subject : subjects){
            grades = gradeRepository.findGradesBySubjectId(subject.getId());
            gradeRepository.deleteAll(grades);
            subjectRepository.delete(subject);
        }
        teacherRepository.delete(teacher);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}