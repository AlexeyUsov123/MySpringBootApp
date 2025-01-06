package UsovAA.MySpringBoot2DBase.dao;

import org.springframework.stereotype.Repository;
import UsovAA.MySpringBoot2DBase.entity.Student;
import java.util.List;

@Repository
public interface StudentDAO {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);

}
