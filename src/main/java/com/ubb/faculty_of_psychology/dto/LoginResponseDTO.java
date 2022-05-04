package com.ubb.faculty_of_psychology.dto;

import com.ubb.faculty_of_psychology.service.JwtTokenService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {
    private String role;
    private String name;
    private String token;

    public LoginResponseDTO(String role, String name, String token)
    {
        this.role = role;
        this.name = name;
        this.token = token;
    }
}
