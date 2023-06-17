package pl.dmcs.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.project_backend.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findById(long id);
    Teacher findByAccountUsername(String username);
}
