package ir.comprehensive.restapp;

import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.model.PersonModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
        PersonDao s = null;
        s.save(PersonModel.builder().build());
    }
}