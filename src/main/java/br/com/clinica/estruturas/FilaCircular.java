package br.com.clinica.estruturas;

public class FilaCircular<T> {
    private T[] items; // O array que armazena os elementos da fila
    private int size; // O número atual de elementos na fila
    private int first; // O índice do primeiro elemento na fila
    private int last; // O índice do último elemento na fila

    @SuppressWarnings("unchecked")
    public FilaCircular(int size) {
        this.items = (T[]) new Object[size]; // Cria o array com tamanho especificado
        this.size = 0; // Inicializa o tamanho atual com zeros
        this.first = 0; // Inicializa o índice do primeiro elemento com zero
        this.last = -1; // Inicializa o índice do último elemento com -1 (não há elementos ainda)
    }

    public boolean isFull() {
        return this.size == this.items.length; // A fila está cheia se o tamanho atual for igual ao tamanho do array
    }

    public boolean isEmpty() {
        return this.size == 0; // A fila está vazia se o tamanho atual for zero
    }

    public void add(T item) {
        if (isFull()) { // Verifica se a fila está cheia
            System.out.println("A fila está cheia!"); // Se estiver cheia, exibe uma mensagem de erro
            return; // E sai do método
        }
        this.last = (this.last + 1) % this.items.length; // Calcula o índice do último elemento circularmente
        this.items[this.last] = item; // Adiciona o item no índice calculado
        this.size++; // Incrementa o tamanho atual
    }

    public boolean remove() {
        if (isEmpty()) { // Verifica se a fila está vazia
            System.out.println("A fila está vazia!"); // Se estiver vazia, exibe uma mensagem de erro
            return false; // E retorna um valor inválido (-1)
        }

        this.first = (this.first + 1) % this.items.length; // Calcula o índice do primeiro elemento circularmente
        this.size--; // Decrementa o tamanho atual
        return true; // Retorna true se consguiu remover o primeiro elemento
    }

    public int size() {
        return this.size; // Retorna o tamanho atual da fila
    }

    public T peek() {
        if (isEmpty()) { // Verifica se a fila está vazia
            System.out.println("A fila está vazia!"); // Se estiver vazia, exibe uma mensagem de erro
            return null; // E retorna um valor inválido (null)
        }
        return this.items[this.first]; // Retorna o primeiro elemento da fila
    }

    public T rear() {
        if (isEmpty()) { // Verifica se a fila está vazia
            System.out.println("A fila está vazia!"); // Se estiver vazia, exibe uma mensagem de erro
            return null; // E retorna um valor inválido (null)
        }
        return this.items[this.last]; // Retorna o último elemento da fila
    }

    public String toArray() {
        String result = "["; // String que conterá os dados

        for (T a : items) {
            result += a + ", ";
        }
        result += "]";

        return result; // Retorna elementos da fila em uma String
    }

    // public static void main(String[] args) {
    // FilaCircular<Integer> fila = new FilaCircular<Integer>(5);
    // System.out.println(fila.isEmpty());
    // fila.add(5);
    // fila.add(8);
    // fila.add(2);
    // System.out.println(fila.isEmpty() + " " + fila.isFull());
    // System.out.println(fila.toArray());
    // System.out.println(fila.isEmpty() + " " + fila.isFull());
    // System.out.println(fila.toArray());
    // fila.add(6);
    // fila.add(7);
    // fila.add(3);
    // fila.remove();
    // fila.remove();
    // fila.remove();
    // fila.add(9);
    // fila.add(10);
    // fila.add(11);
    // System.out.println(fila.isEmpty() + " " + fila.isFull());
    // System.out.println(fila.toArray());
    // }
}
