package appPackage.repositories;

import appPackage.data.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {

    List<Computer> findByNeedsReparationTrue();
    List<Computer> findByNeedsReparationFalse();
}
