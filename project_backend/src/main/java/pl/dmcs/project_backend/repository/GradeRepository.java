package pl.dmcs.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.project_backend.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findById(long id);
    Grade findByStudentId(long id);
}
