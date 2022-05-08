package com.ubb.faculty_of_psychology.filters;

import org.springframework.lang.Nullable;

public class TeacherFilter {

    @Nullable
    private String searchWord;

    @Nullable
    private String specialization;

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
}
