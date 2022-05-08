package com.ubb.faculty_of_psychology.model;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Admin extends User {

    Admin(){
        super.setRole("admin");
    }
}
