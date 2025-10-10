package com.mipt.lukapavlov.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> mergeLists(List<? extends T> list1,
                                         List<? extends T> list2) {
        List<T> resultList = new ArrayList<>();
        for (T element : list1) {
            resultList.add(element);
        }
        for (T element : list2) {
            resultList.add(element);
        }
        return resultList;
    }
    public static <T> void addAll(List<? super T> destination,
                                  List<? extends T> source) {
        for (T element : source) {
            destination.add(element);
        }
    }

    public static void main(String[] args) {
        final List<Integer> list1 = Arrays.asList(1, 2, 3);
        final List<Double> list2 = Arrays.asList(4.5, 5.6);
        final List<Number> merged = CollectionUtils.mergeLists(list1, list2);
        System.out.println(merged);
        final List<Object> destination = new ArrayList<>();
        System.out.println(destination);
        CollectionUtils.addAll(destination, list1);
        System.out.println(destination);
    }
}
