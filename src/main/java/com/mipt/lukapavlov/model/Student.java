package com.mipt.lukapavlov.model;

import java.util.*;

public class Student {
    private static int studentCount = 0;
    private int id;
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
        this.id = studentCount++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getGrade() {
        return this.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, grade);
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        Student student = (Student) object;
        return (this.grade == student.grade) && (this.name.equals(student.name));
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + grade;
    }

}
