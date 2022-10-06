import appPackage.data.Computer;
import appPackage.data.LoanReceipt;
import appPackage.data.Student;
import appPackage.repositories.LoanReceiptRepository;
import appPackage.services.implementations.LoanReceiptImpl;
import appPackage.services.interfaces.LoanReceiptService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoanReceiptServiceTester {

    @Mock
    private LoanReceiptRepository loanReceiptRepository;
    @InjectMocks
    private LoanReceiptService loanReceiptService = new LoanReceiptImpl();
    private static Student student1;
    private static Student student2;
    private static Computer computerInUse;
    private static Computer computerNotInUse;
    private static LoanReceipt loanReceiptOfComputerInUse;
    private static LoanReceipt loanReceiptOfComputerNotInUse;
    private static List<LoanReceipt> student1LoanReceipts;
    private static List<LoanReceipt> student2LoanReceipts;

    @BeforeClass
    public static void createData(){
        computerInUse = new Computer();
        computerNotInUse = new Computer();
        loanReceiptOfComputerInUse = new LoanReceipt(LocalDate.of(2022, Month.JANUARY, 13), student1, computerInUse);
        loanReceiptOfComputerNotInUse = new LoanReceipt(LocalDate.of(2021, Month.SEPTEMBER, 3),
                LocalDate.of(2022, Month.JUNE, 30), student2, computerNotInUse);
        student1 = new Student();
        List<LoanReceipt> loanReceiptOfStudent1 = new ArrayList<>();
        loanReceiptOfStudent1.add(loanReceiptOfComputerInUse);
        student1.setComputersTheyLoaned(loanReceiptOfStudent1);

    }


    @Test
    public void testToSeeIfComputerIsInUse(){
       when(loanReceiptRepository.findByComputerAndEndDateIsNull(computerInUse)).thenReturn(loanReceiptOfComputerInUse);
       when(loanReceiptRepository.findByComputerAndEndDateIsNull(computerNotInUse)).thenReturn(null);
       Assert.assertTrue(loanReceiptService.isPcInUse(computerInUse));
       Assert.assertFalse(loanReceiptService.isPcInUse(computerNotInUse));

    }


    @Test
    public void testToSeeIfStudentOwnsComputer(){
        when(loanReceiptRepository.findByStudentAndEndDateIsNull(student1)).thenReturn(loanReceiptOfComputerInUse);
        Assert.assertEquals(computerInUse, loanReceiptService.checkIfStudentCurrentlyOwnsPC(student1));


    }







}
