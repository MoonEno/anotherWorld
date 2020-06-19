package waw.anotherworld.demon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemonApplication.class, args);
    }

}
