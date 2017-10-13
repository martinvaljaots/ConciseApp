package concise.spring;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Flyway flyway = new Flyway();

        flyway.setDataSource("jdbc:postgresql://localhost:5432/","postgres",
                "root");
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("Running app from command line.");
        };
    }

}
