package com.example.eduaccess;

public class Users {
    String firstName, lastName, age, education;

    public Users() {
    }

    public Users(String firstName, String lastName, String age, String education) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.education = education;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}