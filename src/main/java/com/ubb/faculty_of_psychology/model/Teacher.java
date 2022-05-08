package com.ubb.faculty_of_psychology.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    Teacher() {
        super.setRole("teacher");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(firstName, teacher.firstName)
            && Objects.equals(lastName, teacher.lastName)
            && Objects.equals(specialization, teacher.specialization)
            && Objects.equals(domainOfInterest, teacher.domainOfInterest)
            && Objects.equals(students, teacher.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, specialization, domainOfInterest, students);
    }
}
