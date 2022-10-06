import appPackage.data.Brand;
import appPackage.data.Computer;
import appPackage.repositories.ComputerRepository;
import appPackage.services.implementations.ComputerServiceImpl;
import appPackage.services.interfaces.ComputerService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTester {

    private static List<Computer> allComputers;
    private static Computer computer1;
    private static Computer computer2;
    private static Computer computer3;
    private static Computer computer4;

    @Mock
    private ComputerRepository computerRepository;
    @InjectMocks
    private ComputerService computerService = new ComputerServiceImpl();

    @BeforeClass
    public static void createHypotheticalComputerLists(){
         computer1 = new Computer("123hr3def", Brand.DELL, "bought in 2017");
         computer2 = new Computer("pe453934", Brand.APPLE, "bought in 2019, repaired in 2021");
         computer3 = new Computer("34ijdie", Brand.SAMSUNG, "brand new");
         computer4 = new Computer("eidneioge234", Brand.TOSHIBA, "refurbished");
    ;
        computer1.setNeedsReparation(true);
        computer2.setNeedsReparation(false);
        computer3.setNeedsReparation(false);
        computer4.setNeedsReparation(true);

    }

    @Test
    public void testIfYouGetBrokenComputers(){
        List<Computer> allBrokenComputers = new ArrayList<>();
        allBrokenComputers.add(computer1);
        allBrokenComputers.add(computer4);
        when(computerRepository.findByNeedsReparationTrue()).thenReturn(allBrokenComputers);
        List<Computer> foundBrokenComputers = computerService.getAllBrokenComputers();
        Assert.assertTrue(foundBrokenComputers.contains(computer1));
        Assert.assertTrue(foundBrokenComputers.contains(computer4));
        Assert.assertFalse(foundBrokenComputers.contains(computer2));
        Assert.assertFalse(foundBrokenComputers.contains(computer3));
        Assert.assertEquals(2, foundBrokenComputers.size());

    }

    @Test
    public void testIfYouGetFixedComputers(){
        List<Computer> allWorkingComputers = new ArrayList<>();
        allWorkingComputers.add(computer2);
        allWorkingComputers.add(computer3);
        when(computerRepository.findByNeedsReparationFalse()).thenReturn(allWorkingComputers);
        List<Computer> foundWorkingComputers = computerService.getAllWorkingComputers();
        Assert.assertEquals(2, foundWorkingComputers.size());
        Assert.assertTrue(foundWorkingComputers.contains(computer2));
        Assert.assertTrue(foundWorkingComputers.contains(computer3));
        Assert.assertFalse(foundWorkingComputers.contains(computer1));
        Assert.assertFalse(foundWorkingComputers.contains(computer4));

    }
}
