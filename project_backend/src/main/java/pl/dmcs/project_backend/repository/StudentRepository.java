package pl.dmcs.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.project_backend.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);
    Student findByAccountUsername(String username);
}
