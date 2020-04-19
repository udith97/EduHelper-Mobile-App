package com.example.eduhelper;

public class announcementHelper {

    private int year;
    private String title;
    private String faculty;
    private String description;

    public announcementHelper() {
    }

    public announcementHelper(String title,String faculty,int year,String description) {
        this.title = title;
        this.faculty = faculty;
        this.year = year;
        this.description = description;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
