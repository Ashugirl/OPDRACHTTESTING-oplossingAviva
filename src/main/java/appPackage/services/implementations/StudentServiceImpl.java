package appPackage.services.implementations;

import appPackage.data.Course;
import appPackage.data.Student;
import appPackage.repositories.StudentRepository;
import appPackage.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

    public StudentServiceImpl() {
    }

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.getOne(username);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //TODO: See if you get all students by a course --> DONE in DEMO
    @Override
    public List<Student> getAllStudentsByCourse(Course course) {
        return studentRepository.findByCourse(course);
    }


    @Override
    public Student createStudent(Student student) {
        return null;
    }

    //TODO: Check if the status of the student is changed --> DONE in DEMO
    @Override
    public Student addStudentToBlackList(Student student) {
        student.setOnBlackList(true);
        return studentRepository.save(student);

    }
}
