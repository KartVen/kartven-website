package pl.kartven.portfoliopage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioPageApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioPageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
