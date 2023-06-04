package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  private T val;
  private Node<T> next;

  // constructors
  public Node() {}

  public Node(T v) {
    val = v;
    this.next = null;
  }

  public Node(T val, Node<T> next) {
    this.val = val;
    this.next = next;
  }

  // getters and setters
  public void setNext(Node<T> n) {
    next = n;
  }

  public Node<T> getNext() {
    return next;
  }

  public T getValue() {
    return val;
  }
}
