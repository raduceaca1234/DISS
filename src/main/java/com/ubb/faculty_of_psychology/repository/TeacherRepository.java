package com.ubb.faculty_of_psychology.repository;

import com.ubb.faculty_of_psychology.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Optional<Teacher> findTeacherByEmail(String email);
}