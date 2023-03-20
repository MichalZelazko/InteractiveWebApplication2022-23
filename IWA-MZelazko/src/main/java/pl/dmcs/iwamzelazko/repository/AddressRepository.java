package pl.dmcs.iwamzelazko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.iwamzelazko.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findById(long id);
}
