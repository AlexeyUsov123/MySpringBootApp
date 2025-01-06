package UsovAA.MySpringBoot2DBase.service;

import org.springframework.stereotype.Service;
import UsovAA.MySpringBoot2DBase.entity.Student;
import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);
}
