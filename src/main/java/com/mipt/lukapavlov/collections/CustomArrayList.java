package com.mipt.lukapavlov.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Custom implementation of ArrayList
 * @param <A> the type of elements in this list
 */
public class CustomArrayList<A> implements CustomList<A>, Iterable<A> {
    private Object[] array;
    private int sizeValue;
    private int capacity;
    private static final double EXPANSION_COEFFICIENT = 1.5;

    /** Creates a new CustomArrayList with default capacity 10 */
    public CustomArrayList() {
        this.array = new Object[10];
        capacity = 10;
        sizeValue = 0;
    }

    /**
     * Adds the specified element to the end of this list
     * @param element element to be added
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void add(A element) {
        if (element == null) {
            throw new IllegalArgumentException("Null elements are not allowed");
        }
        if (sizeValue == capacity) {
            capacity = (int) (capacity * EXPANSION_COEFFICIENT);
            Object[] newArray = new Object[capacity];
            for (int i = 0; i < sizeValue; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[sizeValue++] = element;
    }

    /**
     * Returns the element at the specified position
     * @param index index of the element to return
     * @return the element at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    @Override
    public A get(int index) {
        if (index < 0 || index >= sizeValue) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (A) array[index];
    }

    /**
     * Removes the element at the specified position
     *
     * @param index the index of the element to be removed
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public A remove(int index) {
        A removedElement = (A) array[index];
        for (int i = index + 1; i < sizeValue; i++) {
            array[i - 1] = array[i];
        }
        Object[] newArray = new Object[sizeValue - 1];
        for (int i = 0; i < sizeValue - 1; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        sizeValue--;
        return removedElement;
    }

    /** Returns the number of elements in this list */
    @Override
    public int size() {
        return sizeValue;
    }

    /** Returns capacity of elements in this list */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /** Returns true if this list contains no elements */
    @Override
    public boolean isEmpty() {
        return sizeValue == 0;
    }

    @Override
    public CustomArrayIterator iterator() {
        return new CustomArrayIterator();
    }

    private class CustomArrayIterator implements Iterator<A> {
        private int currentIndex = 0;
        private int lastReturnedIndex = -1;

        /**
         * Returns true if the iteration has more elements
         * @return true if there are more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < sizeValue;
        }

        /**
         * Returns the next element in the iteration
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public A next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            lastReturnedIndex = currentIndex;
            return (A) array[currentIndex++];
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator
         * @throws IllegalStateException if next() hasn't been called or remove() was already called
         */
        @Override
        public void remove() {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException("Next() must be called before remove()");
            }

            CustomArrayList.this.remove(lastReturnedIndex);
            currentIndex--;
            lastReturnedIndex = -1;
        }
    }

    /** Returns a string representation of this list */
    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(" ");
        for (int i = 0; i < sizeValue; i++) {
            result.add(array[i].toString());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CustomArrayList<Integer> testArray = new CustomArrayList<>();
        System.out.println(testArray.isEmpty());
        testArray.add(12);
        testArray.add(100);
        testArray.add(231);
        Iterator<Integer> iterator = testArray.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
            if (element == 231) {
                iterator.remove();
            }
        }
        System.out.println(testArray);
    }
}
