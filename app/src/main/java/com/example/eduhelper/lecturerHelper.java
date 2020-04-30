package com.example.eduhelper;

public class lecturerHelper {

    private String name;
    private String moduleCode;
    private String location;
    private String  email;
    private String contact;

    public lecturerHelper() {
    }

    public lecturerHelper(String name, String moduleCode, String location, String email, String contact) {
        this.name = name;
        this.moduleCode = moduleCode;
        this.location = location;
        this.email = email;
        this.contact = contact;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
