package com.mipt.lukapavlov;

import com.mipt.lukapavlov.model.Human;

public abstract class WorkingPerson extends Human {

    public WorkingPerson(String name, String lastName, int age) {
        super(name, lastName, age, true);
    }

    public abstract void work(int hours);

    public boolean goHome(String firstString, String secondString) {
        return firstString.equals(secondString);
    }
}
