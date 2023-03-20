package pl.dmcs.iwamzelazko.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.iwamzelazko.model.Account;
import pl.dmcs.iwamzelazko.model.Address;
import pl.dmcs.iwamzelazko.model.Student;
import pl.dmcs.iwamzelazko.model.Team;
import pl.dmcs.iwamzelazko.repository.StudentRepository;
import pl.dmcs.iwamzelazko.repository.AddressRepository;
import pl.dmcs.iwamzelazko.repository.TeamRepository;
import pl.dmcs.iwamzelazko.repository.AccountRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentRESTController {
    private StudentRepository studentRepository;
    private AddressRepository addressRepository;
    private TeamRepository teamRepository;
    private AccountRepository accountRepository;
    @Autowired
    public StudentRESTController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.GET/*, produces = "application/xml"*/)
    //@GetMapping
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student student = studentRepository.findById(id);
        if(student == null){
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    //@PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getAddress().getId() <= 0){
            addressRepository.save(student.getAddress());
        }
        studentRepository.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        //studentRepository.delete(student);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudents(){
        studentRepository.deleteAll();
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") long id) {
        student.setId(id);
        if (student.getAddress().getId() <= 0){
            addressRepository.save(student.getAddress());
        }
        studentRepository.save(student);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudents(@RequestBody List<Student> students){
        studentRepository.deleteAll();
        for (Student student : students){
            if(student.getAddress().getId() <= 0) {
                addressRepository.save(student.getAddress());
            }
            studentRepository.save(student);
        }
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    //@PatchMapping("/{id}")
    public ResponseEntity<Student> updatePartOfStudent(@RequestBody Map<String, Object> updates, @PathVariable("id") long id){
        Student student = studentRepository.findById(id);
        if (student == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(student, updates);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Student student, Map<String, Object> updates) {
        if (updates.containsKey("name")) {
            student.setName((String) updates.get("name"));
        }
        if (updates.containsKey("surname")) {
            student.setSurname((String) updates.get("surname"));
        }
        if (updates.containsKey("email")) {
            student.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("telephone")) {
            student.setTelephone((String) updates.get("telephone"));
        }
        if (updates.containsKey("address")) {
            student.setAddress((Address) updates.get("address"));
        }
        if (updates.containsKey("teamList")) {
            //FIXME: pewnie nie działa
            student.setTeamList((List<Team>) updates.get("teamList"));
        }
        if (updates.containsKey("account")) {
            student.setAccount((Account) updates.get("account"));
        }
        studentRepository.save(student);
    }
}
