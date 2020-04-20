package com.example.eduhelper;

public class PastpaperHelper {

    private int year;
    private int semester;
    private String ModuleCode;
    private String faculty;
    private String exam;

    public PastpaperHelper() {
    }

    public PastpaperHelper(int year, int semester, String moduleCode, String faculty, String exam) {
        this.year = year;
        this.semester = semester;
        ModuleCode = moduleCode;
        this.faculty = faculty;
        this.exam = exam;
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
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }
}
