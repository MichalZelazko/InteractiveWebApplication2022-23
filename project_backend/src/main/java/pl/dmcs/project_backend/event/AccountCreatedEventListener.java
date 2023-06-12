package pl.dmcs.project_backend.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.dmcs.project_backend.repository.AccountRepository;
import pl.dmcs.project_backend.model.*;
import pl.dmcs.project_backend.repository.StudentRepository;
import pl.dmcs.project_backend.repository.TeacherRepository;
import pl.dmcs.project_backend.repository.AccountRepository;

import java.util.Set;

@Component
public class AccountCreatedEventListener implements ApplicationListener<AccountCreatedEvent> {

    private AccountRepository accountRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public AccountCreatedEventListener(AccountRepository accountRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void onApplicationEvent(AccountCreatedEvent event) {

        Account user = event.getUser();
        Person person = event.getPerson();
        System.out.println(user.getUsername());
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(RoleName.ROLE_STUDENT)) {
                Student student = new Student();
                student.setName(person.getName());
                student.setSurname(person.getSurname());
                student.setAccount(user);
                studentRepository.save(student);
            } else if (role.getName().equals(RoleName.ROLE_TEACHER)) {
                Teacher teacher = new Teacher();
                teacher.setName(person.getName());
                teacher.setSurname(person.getSurname());
                teacher.setAccount(user);
                teacherRepository.save(teacher);
            }
        }
    }
}