package com.example.eduhelper;

public class PastpaperHelper {

    private String year;
    private String faculty;
    private String moduleCode;
    private String exam;
    private String semester;


    public PastpaperHelper() {
    }

    public PastpaperHelper(String year, String faculty, String moduleCode, String exam, String semester) {
        this.year = year;
        this.faculty = faculty;
        this.moduleCode = moduleCode;
        this.exam = exam;
        this.semester = semester;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
