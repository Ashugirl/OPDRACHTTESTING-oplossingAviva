package appPackage.services.implementations;

import appPackage.data.Computer;
import appPackage.data.LoanReceipt;
import appPackage.data.Student;
import appPackage.repositories.LoanReceiptRepository;
import appPackage.services.interfaces.LoanReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanReceiptImpl implements LoanReceiptService {

    private LoanReceiptRepository loanReceiptRepository;


    public LoanReceiptImpl() {
    }

    @Autowired
    public LoanReceiptImpl(LoanReceiptRepository loanReceiptRepository) {
        this.loanReceiptRepository = loanReceiptRepository;
    }

    @Override
    public List<LoanReceipt> getAllOpenReceipts() {

        return loanReceiptRepository.findAll();
    }

    @Override
    public List<LoanReceipt> getAllOpenReceiptsLongerThan9Months() {
        return null;
    }

    @Override
    public boolean loanComputerToStudent(Computer computer, Student student) {
        return false;
    }

    //TODO: Check if this method returns a true if the computer is in use, and if it returns a false if it's not in use.
    @Override
    public boolean isPcInUse(Computer computer) {
        LoanReceipt foundReceipt = loanReceiptRepository.findByComputerAndEndDateIsNull(computer);
        if (foundReceipt!=null){
            return true;
        }
        return false;
    }

    //THIS METHOD WILL FAIL
    @Override
    public Computer checkIfStudentCurrentlyOwnsPC(Student student) {
        LoanReceipt foundReceipt = loanReceiptRepository.findByStudentAndEndDateIsNull(student);
        if (foundReceipt == null){
            return foundReceipt.getLoanedComputer();
        }
        return null;
    }
}
