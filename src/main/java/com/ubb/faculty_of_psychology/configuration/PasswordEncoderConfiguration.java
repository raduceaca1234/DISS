package com.ubb.faculty_of_psychology.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        var defaultencoder = new Argon2PasswordEncoder();

        var encoders = new HashMap<String, PasswordEncoder>();
        encoders.put("argon2", defaultencoder);
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

        var passwordencoder = new DelegatingPasswordEncoder("argon2", encoders);
        passwordencoder.setDefaultPasswordEncoderForMatches(defaultencoder);
        return passwordencoder;
    }
}
