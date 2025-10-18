package com.mipt.lukapavlov.collections;

public interface CustomArrayIterator<A> extends Iterable<A> {
    boolean hasNext();

    A next();

    void remove();
}
