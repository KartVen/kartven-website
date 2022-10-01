package pl.kartven.portfoliopage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kartven.portfoliopage.role.Role;
import pl.kartven.portfoliopage.role.RoleRepository;
import pl.kartven.portfoliopage.user.User;
import pl.kartven.portfoliopage.user.UserRepository;

@SpringBootApplication
public class PortfolioPageApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PortfolioPageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("kartven", "$2a$10$Co19QoX9n/lXUcGgH49Y4OPQeQDJ2uKpo2ZRoaTBgvrMiaMBtZ3B.");
        user.addRoles(
                roleRepository.save(new Role(Role.Type.ROLE_USER)),
                roleRepository.save(new Role(Role.Type.ROLE_ADMIN))
        );
        userRepository.save(user);
    }
}
