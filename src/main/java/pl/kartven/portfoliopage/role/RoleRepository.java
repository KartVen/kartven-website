package pl.kartven.portfoliopage.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(Role.Type roleName);
}
