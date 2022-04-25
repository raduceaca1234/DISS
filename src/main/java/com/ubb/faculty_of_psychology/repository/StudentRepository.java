package com.ubb.faculty_of_psychology.repository;

import com.ubb.faculty_of_psychology.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
