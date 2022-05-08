package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.filters.StudentFilter;
import com.ubb.faculty_of_psychology.filters.TeacherFilter;
import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.model.Teacher;
import com.ubb.faculty_of_psychology.service.AdminService;
import com.ubb.faculty_of_psychology.service.StudentService;
import com.ubb.faculty_of_psychology.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }

    @PostMapping("/student/new")
    public Student saveNewStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/teacher/new")
    public Teacher saveNewTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping("/student/{studentId}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long studentId) {
        return studentService.updateStudent(student, studentId);
    }

    @PutMapping("/teacher/{teacherId}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable Long teacherId) {
        return teacherService.updateTeacher(teacher, teacherId);
    }

    @DeleteMapping("/student/{studentId}")
    public Long deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @DeleteMapping("/teacher/{teacherId}")
    public Long deleteTeacher(@PathVariable Long teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }

    @GetMapping("/filter/students")
    public List<Student> getFilteredStudents(@RequestBody StudentFilter studentFilter) {
        return studentService.getFilteredStudents(studentFilter);
    }

    @GetMapping("/filter/teachers")
    public List<Teacher> getFilteredTeachers(@RequestBody TeacherFilter teacherFilter) {
        return teacherService.getFilteredTeachers(teacherFilter);
    }

//
//    @GetMapping("/admin")
//    public String adminPage(Principal principal, Model model) {
//        String email = principal.getName();
//        model.addAttribute("currentUser", userService.findByEmail(email));
//        model.addAttribute("allUsers", userService.getAllUsers());
//        return "adminPage";
//    }
//
//    @GetMapping("/delete/{user_id}")
//    public String deleteUser(@PathVariable("user_id") Long user_id) {
//        User user = userService.findUserById(user_id);
//        userService.deleteUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/new/{user_id}")
//    public String giveAdminPrivilege(@PathVariable("user_id") Long user_id) {
//        userService.giveAdminPrivilege(user_id);
//        return "redirect:/admin";
//
//    }
}
