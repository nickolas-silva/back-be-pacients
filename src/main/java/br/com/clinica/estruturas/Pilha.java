package br.com.clinica.estruturas;

public class Pilha<T> {
  int size;
  private T[] elements;

  @SuppressWarnings("unchecked")
  public Pilha() {
    this.size = 0;
    this.elements = (T[]) new Object[10];
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    final T[] newSpace = (T[]) new Object[size * 2];

    for (Integer i = 0; i < size; i++) {
      newSpace[i] = this.elements[i];
    }

    this.elements = newSpace;
  }

  public void push(T valor) {
    if (isFull()) {
      this.resize();
    }

    this.elements[size] = valor;
    size++;
  }

  public T pop() {
    if (isEmpty()) {
      return null;
    }

    --size;
    final T element = this.elements[size];
    this.elements[size] = null;

    return element;
  }

  public T peek() {
    return this.elements[size - 1];
  }

  public boolean isFull() {
    return this.elements.length == size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

}
