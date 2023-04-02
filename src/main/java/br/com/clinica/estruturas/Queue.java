package br.com.clinica.estruturas;

public class Queue<T> {

   private Object[] elements;
   private Integer size;

   public Queue() {
      this.elements = new Object[10];
      this.size = 0;
   }

   private void resize() {

      final Object[] newSpace = new Object[size * 2];

      for (Integer i = 0; i < size; i++) {
         newSpace[i] = this.elements[i];
      }

      this.elements = newSpace;
   }

   public void enqueue(T element) {

      this.elements[size] = element;
      size++;

      if (this.isFull()) {
         this.resize();
      }
   }

   @SuppressWarnings("unchecked")
   private T castToGeneric(Object obj) {
      return (T) obj;
   }

   public T dequeue() {
      final Object element = this.elements[0];

      for (Integer i = 1; i < this.size; i++) {
         this.elements[i - 1] = this.elements[i];
      }

      this.size--;

      return this.castToGeneric(element);
   }

   public Boolean isEmpty() {
      return this.size == 0;
   }

   public Integer size() {
      return this.size;
   }

   private Boolean isFull() {
      return this.size == elements.length;
   }

   public T peek() {
      return this.castToGeneric(this.elements[0]);
   }
}