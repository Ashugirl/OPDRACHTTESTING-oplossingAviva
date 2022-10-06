import appPackage.data.Course;
import appPackage.data.Student;
import appPackage.repositories.StudentRepository;
import appPackage.services.implementations.StudentServiceImpl;
import appPackage.services.interfaces.StudentService;
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
public class StudentServiceTester {


    private static Student java1;
    private static Student java2;
    private static Student cSharp1;
    private static Student cSharp2;
    private static List<Student> studentList = new ArrayList<>();
    private static Course java;
    private static Course cSharp;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService = new StudentServiceImpl();

    @BeforeClass
    public static void makeDummyData(){
        java = new Course("Java");
        cSharp = new Course("C#");
        java1 = new Student("PearlDS","password123","Pearl","De Smet",false,java);
        cSharp1= new Student("QuintenDC","password123","Quinten","De Clerc",false,cSharp);
        java2 = new Student("ManuelC","password123","Manuel","Cogneau",true,java);
        cSharp2 = new Student("KenanK","password123","Kenan","Kurda",true,cSharp);
        studentList.add(java1);
        studentList.add(cSharp1);
        studentList.add(java2);
        studentList.add(cSharp2);

    }


    @Test
    public void gettingAllStudentsByCourseTesting(){
        List<Student> onlyJavaStudents = new ArrayList<>();
        onlyJavaStudents.add(java1);
        onlyJavaStudents.add(java2);
        when(studentRepository.findByCourse(java)).thenReturn(onlyJavaStudents);
        List<Student> foundStudents = studentService.getAllStudentsByCourse(java);
        Assert.assertTrue(foundStudents.contains(java1));
        Assert.assertTrue(foundStudents.contains(java2));
        Assert.assertFalse(foundStudents.contains(cSharp1));
        Assert.assertFalse(foundStudents.contains(cSharp2));
        Assert.assertEquals(foundStudents.size(), 2);

    }

    @Test
    public void testBlackListFeature(){
        when(studentRepository.save(java1)).thenReturn(java1);
        Student changedStudent = studentService.addStudentToBlackList(java1);
        Assert.assertTrue(changedStudent.isOnBlackList());

    }
}
