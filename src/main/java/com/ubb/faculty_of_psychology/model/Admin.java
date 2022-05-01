package com.ubb.faculty_of_psychology.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    Admin(){
        super.setRole("admin");
    }
}
