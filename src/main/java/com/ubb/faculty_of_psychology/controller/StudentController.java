package com.ubb.faculty_of_psychology.controller;

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
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("teachers")
    public List<Teacher> allTeachers(){
        return teacherService.getTeachers();
    }

    @RequestMapping("teacher/{domain}")
    public List<Teacher> allTeachersByDomain( @PathVariable String domain) {
        List<Teacher> filteredTeachers = new ArrayList<>();
        for(Teacher teacher: teacherService.getTeachers()){
            if(teacher.getDomainOfInterest().equals(domain)) filteredTeachers.add(teacher);
        }
        return filteredTeachers;
    }
}
