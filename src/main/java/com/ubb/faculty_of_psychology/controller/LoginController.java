package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.model.User;
import com.ubb.faculty_of_psychology.service.AdminService;
import com.ubb.faculty_of_psychology.service.StudentService;
import com.ubb.faculty_of_psychology.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }

        if(studentService.findStudentByEmail(user.getEmail()).getRole().equals("student")) return "student";
        else if(adminService.findAdminByEmail(user.getEmail()).getRole().equals("admin")) return "admin";
        else if(teacherService.findTeacherByEmail(user.getEmail()).getRole().equals("teacher")) return "teacher";
        return null;
    }
}