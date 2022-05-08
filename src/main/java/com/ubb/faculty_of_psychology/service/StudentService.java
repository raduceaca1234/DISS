package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.model.Student;
import com.ubb.faculty_of_psychology.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private PasswordEncoder encoder;


    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder encoder){
        this.studentRepository = studentRepository;
        this.encoder = encoder;
    }

    public Student findStudentByEmail(String email){
        if(studentRepository.findStudentByEmail(email).isPresent())
            return studentRepository.findStudentByEmail(email).get();
        else return null;
    }

    public List<Student> getStudents(){
        return (List<Student>) studentRepository.findAll();
    }

//    public List<Student> getStudentsByTeacher(long teacherId) {
//        List<Student> studentsByTeacher = new ArrayList<>();
//        for(Student student : getStudents()){
//            if(student.getTeacherId() == teacherId) studentsByTeacher.add(student);
//        }
//        return studentsByTeacher;
//    }

    public Student findStudentById(long id) {
        if(studentRepository.findStudentById(id).isPresent())
            return studentRepository.findStudentById(id).get();
        return null;
    }
}
