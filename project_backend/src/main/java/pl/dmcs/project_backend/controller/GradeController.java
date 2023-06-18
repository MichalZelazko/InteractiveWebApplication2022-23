package pl.dmcs.project_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.model.Account;
import pl.dmcs.project_backend.model.Grade;
import pl.dmcs.project_backend.model.Student;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.repository.GradeRepository;
import pl.dmcs.project_backend.repository.StudentRepository;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/grades")
public class GradeController {
    private GradeRepository gradeRepository;
    private AccountRepository accountRepository;
    private StudentRepository studentRepository;


    @Autowired
    public GradeController(GradeRepository gradeRepository, AccountRepository accountRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/subject/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public List<Grade> findGradesBySubject(@PathVariable("id") long id){
        return gradeRepository.findGradesBySubjectId(id);
    }

    @GetMapping(value = "/student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public List<Grade> findGradesByStudent(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Account account = accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Error: User is not logged in or not found!"));
        Student student = studentRepository.findByAccountUsername(account.getUsername());
        return gradeRepository.findGradesByStudentId(student.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade){
        System.out.println(grade);
        gradeRepository.save(grade);
        return new ResponseEntity<Grade>(grade, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<Grade> editGrade(@PathVariable("id") long id, @RequestBody int grade){
        Grade currentGrade = gradeRepository.findById(id);
        if (currentGrade == null){
            System.out.println("Grade with id " + id + " not found");
            return new ResponseEntity<Grade>(HttpStatus.NOT_FOUND);
        }
        currentGrade.setGrade(grade);
        gradeRepository.save(currentGrade);
        return new ResponseEntity<Grade>(currentGrade, HttpStatus.OK);
    }
}
