package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Admin;
import com.ubb.faculty_of_psychology.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    private AdminRepository adminRepository;

    @Autowired
    private AdminDetailsServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminRepository.findAdminByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("user")));
    }
}
