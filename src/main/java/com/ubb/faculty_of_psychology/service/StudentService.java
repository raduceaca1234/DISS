package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.filters.StudentFilter;
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
    public StudentService(StudentRepository studentRepository, PasswordEncoder encoder) {
        this.studentRepository = studentRepository;
        this.encoder = encoder;
    }

    public Student findStudentByEmail(String email) {
        if (studentRepository.findStudentByEmail(email).isPresent())
            return studentRepository.findStudentByEmail(email).get();
        else return null;
    }

//    public List<Student> getStudentsByTeacher(long teacherId) {
//        List<Student> studentsByTeacher = new ArrayList<>();
//        for(Student student : getStudents()){
//            if(student.getTeacherId() == teacherId) studentsByTeacher.add(student);
//        }
//        return studentsByTeacher;
//    }

    public Student findStudentById(long id) {
        if (studentRepository.findStudentById(id).isPresent())
            return studentRepository.findStudentById(id).get();
        return null;
    }

    public List<Student> findAll() {
        var students = new ArrayList<Student>();
        var iterable = studentRepository.findAll();
        for (var stud : iterable) {
            students.add(stud);
        }
        return students;
    }

    private boolean isValid(Student student) {
        return true;
    }

    public Student saveStudent(Student student) {
        if (isValid(student)) {
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public Student updateStudent(Student student, Long studentId) {
        if (isValid(student) && studentRepository.findById(studentId).isPresent()) {
            student.setId(studentId);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public Long deleteStudent(Long studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            studentRepository.deleteById(studentId);
            return studentId;
        } else {
            return null;
        }
    }

    public List<Student> getFilteredStudents(StudentFilter studentFilter) {
        var students = new ArrayList<Student>();
        if (studentFilter.getSearchWord() != null) {
            students.addAll(studentRepository.findByFirstNameContaining(studentFilter.getSearchWord()));
            students.addAll(studentRepository.findByLastNameContaining(studentFilter.getSearchWord()));
        }
        if (studentFilter.getGraduation() != null) {
            var graduationStudents = studentRepository.findByGraduation(studentFilter.getGraduation());
            var commonStudents = new ArrayList<Student>();
            for (var elem1 : students) {
                for (var elem2 : graduationStudents) {
                    if (elem1 == elem2) {
                        commonStudents.add(elem1);
                    }
                }
            }
            students = commonStudents;
        }
        if (studentFilter.getSpecialization() != null) {
            var specializationStudents = studentRepository.findBySpecialization(studentFilter.getSpecialization());
            var commonStudents = new ArrayList<Student>();
            for (var elem1 : students) {
                for (var elem2 : specializationStudents) {
                    if (elem1 == elem2) {
                        commonStudents.add(elem1);
                    }
                }
            }
            students = commonStudents;
        }
        if (studentFilter.getFormOfEducation() != null) {
            var formOfEducationStudents = studentRepository.findByFormOfEducation(studentFilter.getFormOfEducation());
            var commonStudents = new ArrayList<Student>();
            for (var elem1 : students) {
                for (var elem2 : formOfEducationStudents) {
                    if (elem1 == elem2) {
                        commonStudents.add(elem1);
                    }
                }
            }
            students = commonStudents;
        }
        return students;
    }
}
