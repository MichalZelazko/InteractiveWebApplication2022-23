package pl.dmcs.project_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.project_backend.message.response.ResponseMessage;
import pl.dmcs.project_backend.model.Account;
import pl.dmcs.project_backend.model.Subject;
import pl.dmcs.project_backend.model.Teacher;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.repository.SubjectRepository;
import pl.dmcs.project_backend.repository.TeacherRepository;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectRepository subjectRepository;
    private AccountRepository accountRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository, AccountRepository accountRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.accountRepository = accountRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<Subject> findSubject(@PathVariable("id") long id){
        Subject subject = subjectRepository.findById(id);
        if (subject == null){
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @GetMapping(value = "/teacher")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public List<Subject> findSubjectsByTeacher(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Account account = accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Error: User is not logged in or not found!"));
        Teacher teacher = teacherRepository.findByAccountUsername(account.getUsername());
        return teacher.getSubjects();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject, HttpServletRequest request){
        if(subjectRepository.existsByName(subject.getName())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> This subject already exists"), HttpStatus.BAD_REQUEST);
        }
        Principal principal = request.getUserPrincipal();
        Account account = accountRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Error: User is not logged in or not found!"));
        System.out.println(account.getUsername());
        System.out.println(subject);
        Teacher teacher = teacherRepository.findByAccountUsername(account.getUsername());
        subject.setTeacher(teacher);
        subjectRepository.save(subject);
        return new ResponseEntity<Subject>(subject, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") long id){
        Subject subject = subjectRepository.findById(id);
        if (subject == null){
            System.out.println("Subject with id " + id + " not found");
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        subjectRepository.delete(subject);
        return new ResponseEntity<Subject>(HttpStatus.NO_CONTENT);
    }

}
