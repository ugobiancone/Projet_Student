package com.test.test1;

import java.time.LocalDate;

public class Student {
    private int Id;
    public String name;
    public String gender;
    public LocalDate bday;
    public String photo;
    public String mark;
    public String comments;

    public Student(int id,String name, String gender, LocalDate bday, String photo, String mark, String comments) {
        this.Id = id;
        this.name = name;
        this.gender = gender;
        this.bday = bday;
        this.photo = photo;
        this.mark = mark;
        this.comments = comments;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Id=" + Id + " , name='" + name ;
    }
}
