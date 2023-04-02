package br.com.clinica.estruturas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set<T> implements Iterable<T> {
    private T[] elements;
    private Integer size;

    @SuppressWarnings("unchecked")
    public Set() {
        elements = (T[]) new Object[10];
        size = 0;
    }

    private Boolean isFull() {
        return size == elements.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        final Integer newSize = size * 2;
        final T[] newSpace = (T[]) new Object[newSize];

        for (Integer i = 0; i < this.size; i++) {
            newSpace[i] = elements[i];
        }

        this.elements = newSpace;
    }

    public boolean add(T element) {

        if (this.contains(element)) {
            return false;
        }

        if (this.isFull()) {
            this.resize();
        }

        this.elements[size] = element;
        size++;

        return true;
    }

    public boolean remove(T element) {
        final Integer index = indexOf(element);

        if (index == -1) {
            return false;
        }

        for (Integer i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        return true;
    }

    public Boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    public Integer size() {
        return this.size;
    }

    private Integer indexOf(T element) {
        for (Integer i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int index;

        public MyListIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            final T element = elements[index];
            index++;

            return element;
        }
    }
}