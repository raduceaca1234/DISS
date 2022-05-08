package com.ubb.faculty_of_psychology.filters;

import org.springframework.lang.Nullable;

public class StudentFilter {

    @Nullable
    private String searchWord;

    @Nullable
    private String specialization;

    @Nullable
    private String graduation;

    @Nullable
    private String formOfEducation;

    @Nullable
    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(@Nullable String searchWord) {
        this.searchWord = searchWord;
    }

    @Nullable
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(@Nullable String specialization) {
        this.specialization = specialization;
    }

    @Nullable
    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(@Nullable String graduation) {
        this.graduation = graduation;
    }

    @Nullable
    public String getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(@Nullable String formOfEducation) {
        this.formOfEducation = formOfEducation;
    }
}
