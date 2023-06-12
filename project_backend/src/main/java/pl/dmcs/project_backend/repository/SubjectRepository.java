package pl.dmcs.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.project_backend.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findById(long id);
    Subject findByTeacherId(long teacherId);
}
