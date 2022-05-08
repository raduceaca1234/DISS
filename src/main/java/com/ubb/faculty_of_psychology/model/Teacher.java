package com.ubb.faculty_of_psychology.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Teacher extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String specialization;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> domainOfInterest;

    @OneToMany
    @ElementCollection(targetClass = Student.class, fetch = FetchType.EAGER)
    private List<Student> students;

    Teacher(){
        super.setRole("teacher");
    }
}
