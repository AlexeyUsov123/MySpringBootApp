package UsovAA.test_security2db_themeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UsovAA.test_security2db_themeleaf.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
