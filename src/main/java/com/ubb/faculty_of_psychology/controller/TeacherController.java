package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.model.Teacher;
import com.ubb.faculty_of_psychology.service.StudentService;
import com.ubb.faculty_of_psychology.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("students/{email}")
    public List<Student> allStudent(@PathVariable String email){
        Teacher teacher = teacherService.findTeacherByEmail(email);
        return teacher.getStudents();
    }

    @RequestMapping("students/{email}/{name}")
    public List<Student> allStudentsByName(@PathVariable String name, @PathVariable String email){
        Teacher teacher = teacherService.findTeacherByEmail(email);
        List<Student> filteredStudents = new ArrayList<>();
        for(Student student: teacher.getStudents())
            if((student.getLastName() + " " + student.getFirstName()).contains(name)) filteredStudents.add(student);
        return filteredStudents;
    }

    @RequestMapping("students/{id}")
    public Student getStudentInfo(@PathVariable long id) {
        return studentService.findStudentById(id);
    }
}
