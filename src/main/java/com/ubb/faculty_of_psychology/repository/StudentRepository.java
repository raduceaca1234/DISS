package com.ubb.faculty_of_psychology.repository;

import com.ubb.faculty_of_psychology.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentById(long id);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameContaining(String lastName);

    List<Student> findBySpecialization(String specialization);

    List<Student> findByGraduation(String graduation);

    List<Student> findByFormOfEducation(String formOfEducation);
}
