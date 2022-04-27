package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public AdminService(AdminRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
}
