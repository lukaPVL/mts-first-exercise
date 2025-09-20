package com.mipt.lukapavlov.model;

public class Human {
    private String name;
    private String lastname;
    private int age;
    private boolean status;

    public Human(String name, String lastname, int age, boolean status) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastname;
    }

    public int getAge() {
        return this.age;
    }

    public boolean getStatus() {
        return this.status;
    }
}
