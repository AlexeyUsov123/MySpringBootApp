package UsovAA.test_security2db_themeleaf.repository;

import UsovAA.test_security2db_themeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import UsovAA.test_security2db_themeleaf.repository.RoleRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
