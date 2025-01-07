package UsovAA.test_security2db_themeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import UsovAA.test_security2db_themeleaf.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
