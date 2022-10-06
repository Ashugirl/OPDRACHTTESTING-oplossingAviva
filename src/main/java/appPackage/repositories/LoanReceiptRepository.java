package appPackage.repositories;

import appPackage.data.Computer;
import appPackage.data.LoanReceipt;
import appPackage.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanReceiptRepository extends JpaRepository<LoanReceipt, Long> {

    LoanReceipt findByComputerAndEndDateIsNull(Computer computer);
    LoanReceipt findByStudentAndEndDateIsNull(Student student);
    List<LoanReceipt> findByEndDateIsNull();
}
