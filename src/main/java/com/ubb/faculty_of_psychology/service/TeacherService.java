package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Teacher;
import com.ubb.faculty_of_psychology.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private TeacherRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public TeacherService(TeacherRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public Teacher findTeacherByEmail(String email){
        if(repository.findTeacherByEmail(email).isPresent())
            return repository.findTeacherByEmail(email).get();
        else return null;
    }
}
