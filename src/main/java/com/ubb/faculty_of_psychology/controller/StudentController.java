package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
}
