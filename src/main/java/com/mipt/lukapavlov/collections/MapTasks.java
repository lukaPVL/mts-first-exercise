package com.mipt.lukapavlov.collections;

import com.mipt.lukapavlov.model.Student;

import java.util.*;

public class MapTasks {

    HashMap<Integer, Student> memoryHash = new HashMap<>();

    TreeMap<Integer, Student> memoryTree = new TreeMap<>(Comparator.reverseOrder());

    public void addStudent(Student student) {
        memoryHash.put(student.getId(), student);
        memoryTree.put(student.getId(), student);
    }

    public ArrayList<Student> findStudentsByGradeRange(Map<Integer, Student> map, double minGrade, double maxGrade) {
        ArrayList<Student> result = new ArrayList<>();
        for (Integer key : map.keySet()) {
            Student student = map.get(key);
            if (student.getGrade() <= maxGrade && student.getGrade() >= minGrade) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<Student> getTopNStudents(TreeMap<Integer, Student> map, int n) {
        ArrayList<Student> result = new ArrayList<>();
        int count = 0;

        for (Student student : map.values()) {
            if (count >= n) break;
            result.add(student);
            count++;
        }
        return result;
    }


    public static void main(String[] args) {
        Random random = new Random();
        MapTasks testObj = new MapTasks();
        String[] pullNames = new String[] {"Vany", "Vasy", "Natalia", "Luka", "Sasha"};

        for (int i = 0; i < pullNames.length; i++) {
            Student student = new Student(pullNames[i], random.nextDouble(10000));
            testObj.addStudent(student);
        }

        ArrayList<Student> listStudent = testObj.findStudentsByGradeRange(testObj.memoryHash, 2, 7000);
        System.out.println(listStudent);

        ArrayList<Student> listTopNStudent = testObj.getTopNStudents(testObj.memoryTree, 3);
        System.out.println(listTopNStudent);

    }
}
