package com.ubb.faculty_of_psychology.service;

import com.ubb.faculty_of_psychology.filters.TeacherFilter;
import com.ubb.faculty_of_psychology.model.Teacher;
import com.ubb.faculty_of_psychology.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public TeacherService(TeacherRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Teacher findTeacherByEmail(String email) {
        if (repository.findTeacherByEmail(email).isPresent())
            return repository.findTeacherByEmail(email).get();
        else return null;
    }

    public List<Teacher> findAll() {
        var teachers = new ArrayList<Teacher>();
        var iterable = repository.findAll();
        for (var teach : iterable) {
            teachers.add(teach);
        }
        return teachers;
    }

    private boolean isValid(Teacher teacher) {
        return true;
    }

    public Teacher saveTeacher(Teacher teacher) {
        if (isValid(teacher)) {
            return repository.save(teacher);
        } else {
            return null;
        }
    }

    public Teacher updateTeacher(Teacher teacher, Long teacherId) {
        if (isValid(teacher) && repository.findById(teacherId).isPresent()) {
            teacher.setId(teacherId);
            return repository.save(teacher);
        } else {
            return null;
        }
    }

    public Long deleteTeacher(Long teacherId) {
        if (repository.findById(teacherId).isPresent()) {
            repository.deleteById(teacherId);
            return teacherId;
        } else {
            return null;
        }
    }

    public List<Teacher> getFilteredTeachers(TeacherFilter teacherFilter) {
        var teachers = new ArrayList<Teacher>();
        if (teacherFilter.getSearchWord() != null) {
            teachers.addAll(repository.findByFirstNameContaining(teacherFilter.getSearchWord()));
            teachers.addAll(repository.findByLastNameContaining(teacherFilter.getSearchWord()));
        }
        if (teacherFilter.getSpecialization() != null) {
            var specializationTeachers = repository.findBySpecialization(teacherFilter.getSpecialization());
            var commonTeachers = new ArrayList<Teacher>();
            for (var elem1 : teachers) {
                for (var elem2 : specializationTeachers) {
                    if (elem1 == elem2) {
                        commonTeachers.add(elem1);
                    }
                }
            }
            teachers = commonTeachers;
        }
        return teachers;
    }
}
