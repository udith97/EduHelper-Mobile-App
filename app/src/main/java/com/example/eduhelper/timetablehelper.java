package com.example.eduhelper;

public class timetablehelper {

    private String tfaculty;
    private String tyear;
    private String tsemester;
    private String tgroup;

    public timetablehelper() {
    }

    public timetablehelper(String tfaculty, String tyear, String tsemester, String tgroup) {
        this.tfaculty = tfaculty;
        this.tyear = tyear;
        this.tsemester = tsemester;
        this.tgroup = tgroup;
    }

    public String getTfaculty() {
        return tfaculty;
    }

    public void setTfaculty(String tfaculty) {
        this.tfaculty = tfaculty;
    }

    public String getTyear() {
        return tyear;
    }

    public void setTyear(String tyear) {
        this.tyear = tyear;
    }

    public String getTsemester() {
        return tsemester;
    }

    public void setTsemester(String tsemester) {
        this.tsemester = tsemester;
    }

    public String getTgroup() {
        return tgroup;
    }

    public void setTgroup(String tgroup) {
        this.tgroup = tgroup;
    }


}