package com.mipt.lukapavlov;

public class MainClass {
    private int intField;
    private String stringField;
    protected double doubleField;
    public final long LONG_FIELD = 10^5;

    public static void main(String[] args) {
        for (int start = 0; start < 15; start++) {
            System.out.println("Iter: " + start);
        }
    }
}
