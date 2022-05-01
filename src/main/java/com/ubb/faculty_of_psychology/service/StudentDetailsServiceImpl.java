package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {
    private StudentRepository studentRepository;

    @Autowired
    private StudentDetailsServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Student admin = studentRepository.findStudentByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")));
    }
}
