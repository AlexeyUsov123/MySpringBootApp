package UsovAA.MySpringBoot2DBase.service;

import UsovAA.MySpringBoot2DBase.entity.Student;
import UsovAA.MySpringBoot2DBase.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        return studentRepository.findById((long) id).orElse(null);
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        //log.info("Saving student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        studentRepository.deleteById((long) id);
    }
}