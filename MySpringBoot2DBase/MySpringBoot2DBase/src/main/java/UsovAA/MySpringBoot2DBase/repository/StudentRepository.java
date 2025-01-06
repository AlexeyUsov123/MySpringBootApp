package UsovAA.MySpringBoot2DBase.repository;

import UsovAA.MySpringBoot2DBase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}