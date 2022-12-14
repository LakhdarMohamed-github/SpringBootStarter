package com.example.demo.Student;

import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());

        if(optionalStudent.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);/**/
        //System.out.println(student.toString());
    }

    public void deleteStudentById(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("The student with id "+studentId+" doesn't exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId,
                                  String name,
                                  String email) {

            Student student = studentRepository.findById(studentId).orElseThrow(
                    ()-> new IllegalStateException("The student with the id "+studentId+" doesn't exist!")
            );

            if(name != null && name.length()>0 && !Objects.equals(student.getName(), name) ){
                student.setName(name);
            }
        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email) ){
            Optional<Student> optionalEmail = studentRepository.findStudentByEmail(student.getEmail());
            if(optionalEmail.isPresent()){
                throw new IllegalStateException("Email Taken!");
            }
            student.setEmail(email);
        }

    }

    /*return List.of(
            new Student(
                        1L,
                                "Mohamed",
                                "Mohamed@gmail.com",
                        LocalDate.of(1999, Month.MARCH, 28),
                        23
                                )
                                );*/
}
