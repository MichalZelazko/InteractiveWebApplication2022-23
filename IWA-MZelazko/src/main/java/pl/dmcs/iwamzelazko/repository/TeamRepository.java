package pl.dmcs.iwamzelazko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.iwamzelazko.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    Team findById(long id);
}
