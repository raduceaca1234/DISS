package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.dto.LoginDTO;
import com.ubb.faculty_of_psychology.dto.LoginResponseDTO;
import com.ubb.faculty_of_psychology.service.AdminService;
import com.ubb.faculty_of_psychology.service.JwtTokenService;
import com.ubb.faculty_of_psychology.service.StudentService;
import com.ubb.faculty_of_psychology.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }

        if(studentService.findStudentByEmail(loginDTO.getEmail()).getRole().equals("student")) return new LoginResponseDTO("student", studentService.findStudentByEmail(loginDTO.getEmail()).getFirstName() + " " + studentService.findStudentByEmail(loginDTO.getEmail()).getLastName(), jwtTokenService.generateToken(studentService.findStudentByEmail(loginDTO.getEmail())));
        else if(adminService.findAdminByEmail(loginDTO.getEmail()).getRole().equals("admin")) return new LoginResponseDTO("admin", "admin", jwtTokenService.generateToken(adminService.findAdminByEmail(loginDTO.getEmail())));
        else if(teacherService.findTeacherByEmail(loginDTO.getEmail()).getRole().equals("teacher")) return new LoginResponseDTO("teacher", teacherService.findTeacherByEmail(loginDTO.getEmail()).getFirstName() + " " + teacherService.findTeacherByEmail(loginDTO.getEmail()).getLastName(), jwtTokenService.generateToken(adminService.findAdminByEmail(loginDTO.getEmail())));
        return null;
    }
}