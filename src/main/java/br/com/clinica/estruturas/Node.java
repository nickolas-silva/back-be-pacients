package br.com.clinica.estruturas;

class Node<T> {
   private T info;

   private Node<T> next;
   private Node<T> previous;

   public Node(T data) {
      info = data;
      next = null;
      previous = null;
   }

   public T info() {
      return info;
   }

   public Node<T> next() {
      return next;
   }

   public void next(Node<T> node) {
      next = node;
   }

   public Node<T> previous() {
      return previous;
   }

   public void previous(Node<T> node) {
      previous = node;
   }

}
