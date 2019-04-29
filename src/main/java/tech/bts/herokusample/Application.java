package tech.bts.herokusample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /** This will be executed when we start the application. */
    @Bean
    public CommandLineRunner test() {

        return args -> {
            System.out.println("App started");
        };
    }
}
