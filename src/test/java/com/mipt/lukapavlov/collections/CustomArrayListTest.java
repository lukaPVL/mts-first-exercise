package com.mipt.lukapavlov.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomArrayListTest {

    private CustomList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomArrayList<>();
    }
    @Test
    void testAdd() {
        Integer element = 100;

        list.add(element);

        assertEquals(1, list.size());
        assertEquals(100, list.get(list.size() - 1));
    }

    @Test
    void testRemove() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Integer removedElement = list.remove(list.size() / 2);

        assertEquals(99, list.size());
        assertEquals(false, list.get(list.size() / 2).equals(removedElement));
    }

    @Test
    void testGet() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Integer givenElement = list.get(list.size() / 2);

        assertEquals(true, givenElement.equals(list.get(list.size() / 2)));
    }

    @Test
    void testSize() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        assertEquals(100, list.size());
    }

    @Test
    void testIsEmpty() {

        assertEquals(true, list.isEmpty());

        list.add(100);

        assertEquals(false, list.isEmpty());
    }

    @Test
    void testDynamicExpansion() {
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }

        assertEquals(11, list.size());
        assertEquals(15, list.getCapacity());
    }

    @Test
    void testWithStringType() {
        CustomList<String> list = new CustomArrayList<>();

        list.add("first");
        list.add("second");
        list.add("third");

        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @Test
    void testWithRandomObjects() {
        CustomList<Person> list = new CustomArrayList<>();
        Person first = new Person("Luka", 18);
        Person second = new Person("Diman", 24);

        list.add(first);
        list.add(second);

        assertEquals(true, first.equals(list.get(0)));
        assertEquals(true, second.equals(list.get(1)));
    }
}
