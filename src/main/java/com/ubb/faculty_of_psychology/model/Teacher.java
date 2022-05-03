package com.ubb.faculty_of_psychology.model;

import javax.persistence.*;

@Entity
public class Teacher extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String specialization;

    Teacher(){
        super.setRole("teacher");
    }
}
