package com.ubb.faculty_of_psychology.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
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

    Student() {
        super.setRole("student");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName)
            && Objects.equals(lastName, student.lastName)
            && Objects.equals(academicCode, student.academicCode)
            && Objects.equals(specialization, student.specialization)
            && Objects.equals(graduation, student.graduation)
            && Objects.equals(formOfEducation, student.formOfEducation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, academicCode, specialization, graduation, formOfEducation);
    }
}
