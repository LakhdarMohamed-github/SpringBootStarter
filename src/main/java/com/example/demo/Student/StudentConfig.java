package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->  {
            Student Mohamed = new Student(
                    "Mohamed",
                    "Mohamed@gmail.com",
                    LocalDate.of(1999, Month.MARCH, 28)
            );
            Student Hiba = new Student(
                    "Hiba",
                    "Hiba@gmail.com",
                    LocalDate.of(2000, Month.MARCH, 20)
            );
            Student Reda = new Student(
                    "Reda",
                    "Reda@gmail.com",
                    LocalDate.of(1997, Month.MARCH, 20)
            );

            studentRepository.saveAll(
                    List.of(Mohamed, Hiba, Reda)
            );
        };
    }
}
