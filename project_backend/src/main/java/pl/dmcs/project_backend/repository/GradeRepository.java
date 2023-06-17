package pl.dmcs.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.project_backend.model.Grade;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findById(long id);
    List<Grade> findGradesByStudentId(long id);
    List<Grade> findGradesBySubjectId(long id);
}
