package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.dto.LoginDTO;
import com.ubb.faculty_of_psychology.dto.LoginResponseDTO;
import com.ubb.faculty_of_psychology.model.User;
import com.ubb.faculty_of_psychology.service.AdminService;
import com.ubb.faculty_of_psychology.service.StudentService;
import com.ubb.faculty_of_psychology.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }

        if(studentService.findStudentByEmail(loginDTO.getEmail()).getRole().equals("student")) return new LoginResponseDTO("student", "name", "token");
        else if(adminService.findAdminByEmail(loginDTO.getEmail()).getRole().equals("admin")) return new LoginResponseDTO("admin", "name", "token");
        else if(teacherService.findTeacherByEmail(loginDTO.getEmail()).getRole().equals("teacher")) return new LoginResponseDTO("teacher", "name", "token");
        return null;
    }
}