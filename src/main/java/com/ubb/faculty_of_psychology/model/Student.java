package com.ubb.faculty_of_psychology.model;

import javax.persistence.*;

@Entity
public class Student extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "academic_code")
    private String academicCode;

    @Column
    private String specialization;

    @Column
    private String graduation;

    @Column(name = "form_of_education")
    private String formOfEducation;

    Student(){
        super.setRole("student");
    }
}
