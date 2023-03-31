package br.com.clinica.estruturas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T> implements Iterable<T> {
    private T[] elements;
    private Integer size;

    @SuppressWarnings("unchecked")
    public List() {
        elements = (T[]) new Object[10];
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = element;
        size++;
    }

    public T get(Integer index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    public Integer size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) new Object[elements.length * 2];

        for (Integer i = 0; i < size; i++) {
            newArray[0] = elements[0];
        }
        elements = newArray;
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

            final Object element = elements[index];
            index++;

            @SuppressWarnings("unchecked")
            final T object = (T) element;
            return object;
        }
    }
}