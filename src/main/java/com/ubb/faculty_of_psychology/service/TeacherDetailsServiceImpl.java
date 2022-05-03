package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Teacher;
import com.ubb.faculty_of_psychology.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TeacherDetailsServiceImpl implements UserDetailsService {

    private TeacherRepository repository;

    @Autowired
    private TeacherDetailsServiceImpl(TeacherRepository repository){
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = repository.findTeacherByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(teacher.getEmail(), teacher.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(teacher.getRole())));
    }
}
