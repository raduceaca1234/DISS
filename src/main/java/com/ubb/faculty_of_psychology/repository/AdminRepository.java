package com.ubb.faculty_of_psychology.repository;

import com.ubb.faculty_of_psychology.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findAdminByEmail(String email);
}
