package com.example.todobackend.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.DECEMBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student luxshan = new Student(
                    "Luxshan",
                    "luxshan.thuraisingam@gmail.com",
                    LocalDate.of(2000, DECEMBER, 9)
            );

            Student lucky = new Student(
                    "Lucky",
                    "luckybraveboys@gmail.com",
                    LocalDate.of(2000, DECEMBER, 9)
            );

            repository.saveAll(
                    List.of(luxshan, lucky)
            );
        };
    }
}
