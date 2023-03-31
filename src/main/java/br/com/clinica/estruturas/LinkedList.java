package br.com.clinica.estruturas;

public class LinkedList<T> {
   int size = 0;
   Node<T> head;
   Node<T> tail;

   public void addFirst(T data) {
      Node<T> newNode = new Node<T>(data);
      newNode.next(head);
      head = newNode;
      size++;
   }

   public void addLast(T data) {
      Node<T> newNode = new Node<T>(data);
      tail.next(newNode);
      newNode.previous(tail);
      tail = newNode;
      size++;
   }

   public void add(T data) {
      Node<T> newNode = new Node<T>(data);

      if (head == null) {
         head = newNode;
         tail = head;
      } else {
         newNode.previous(tail);
         tail.next(newNode);
         tail = newNode;
      }

      size++;
   }

   public int search(T data) {
      int contador = -1;
      Node<T> iterator = head;

      while (iterator.info() != data && iterator != null) {
         contador++;
         iterator = iterator.next();
      }

      if (iterator.info() == tail.info() || iterator == null)
         return -1;
      else
         return contador;

   }

   public T head() {
      return head.info();
   }

   public T tail() {
      return tail.info();
   }

   public int size() {
      return size;
   }

   public String list() {
      String retorno = new String();

      for (Node<T> iterator = head; iterator != null; iterator = iterator.next()) {
         retorno += " " + iterator.info().toString();
      }

      return retorno;
   }
}
