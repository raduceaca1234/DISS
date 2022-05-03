package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public StudentService(StudentRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public Student findStudentByEmail(String email){
        if(repository.findStudentByEmail(email).isPresent())
            return repository.findStudentByEmail(email).get();
        else return null;
    }
}
