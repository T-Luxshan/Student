package com.example.todobackend.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
        boolean exists = studentRepository.existsById(studentID);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentID + " does not exists");
        }
        studentRepository.deleteById(studentID);

    }


    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id "+ studentID + " does not exist")
                );
            if(name != null &&
                    !name.isEmpty() &&
                !Objects.equals(student.getName(), name)){
                student.setName(name);
            }

        if(email != null &&
                !email.isEmpty() &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }
}
