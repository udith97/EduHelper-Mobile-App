package com.example.eduhelper;

public class Paper {

    private int year;
    private int semester;
    private String moduleCode;
    private String exam;
    private String faculty;

    public Paper(int year, int semester, String moduleCode, String exam, String faculty) {
        this.year = year;
        this.semester = semester;
        this.moduleCode = moduleCode;
        this.exam = exam;
        this.faculty = faculty;
    }

    public Paper() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
