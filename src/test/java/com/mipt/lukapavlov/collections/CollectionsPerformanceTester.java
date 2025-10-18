package com.mipt.lukapavlov.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionsPerformanceTester {
    private static final int ELEMENT_COUNT = 10000;

    @Test
    void comparePerformance() {
        System.out.println("ArrayList vs LinkedList");
        System.out.println("Number of elements: " + ELEMENT_COUNT);
        System.out.println("===============================================");

        // Тестируем различные операции
        testAddToEnd();
        testAddToBeginning();
        testInsertInMiddle();
        testRandomAccess();
        testRemoveFromBeginning();
        testRemoveFromEnd();

        System.out.println("===============================================");
    }

    private void testAddToEnd() {
        List<Integer> arrayList = new ArrayList<>();
        long startTimeForArrayList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        long startTimeForLinkedList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("ADD_TO_END: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }

    private void testAddToBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        long startTimeForArrayList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(0, i);
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        long startTimeForLinkedList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(0, i);
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("ADD_TO_BEGIN: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }

    private void testInsertInMiddle() {
        List<Integer> arrayList = new ArrayList<>();
        long startTimeForArrayList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(arrayList.size() / 2, i);
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        long startTimeForLinkedList = System.nanoTime();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(linkedList.size() / 2, i);
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("INSERT_IN_MIDDLE: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }

    private void testRandomAccess() {
        Random random = new Random();

        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }

        long startTimeForArrayList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.get(random.nextInt(10000));
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }

        long startTimeForLinkedList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.get(random.nextInt(10000));
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("RANDOM_ACCESS: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }

    private void testRemoveFromBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }

        long startTimeForArrayList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.remove(0);
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }

        long startTimeForLinkedList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.remove(0);
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("REMOVE_FROM_BEGIN: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }

    private void testRemoveFromEnd() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }

        long startTimeForArrayList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.remove(arrayList.size() - 1);
        }
        long arrayListResultTime = (System.nanoTime() - startTimeForArrayList) / 1_000_000;

        List<Integer> linkedList = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }

        long startTimeForLinkedList = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.remove(linkedList.size() - 1);
        }
        long linkedListResultTime = (System.nanoTime() - startTimeForLinkedList) / 1_000_000;

        System.out.println("REMOVE_FROM_END: " + "ArrayList: " + arrayListResultTime + " LinkedList: " + linkedListResultTime);
    }
}
