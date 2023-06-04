package nz.ac.auckland.se281.datastructures;

public class DNode<T> {

  private T data;
  private DNode<T> prev;
  private DNode<T> next;

  // Constructors
  public DNode() {}

  public DNode(T data) {
    this.data = data;
    prev = null;
    next = null;
  }

  public DNode(T data, DNode<T> prev) {
    this.data = data;
    this.prev = prev;
    next = null;
  }

  public DNode(T data, DNode<T> prev, DNode<T> next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  // Setters
  public void setPrev(DNode<T> prev) {
    this.prev = prev;
  }

  public void setNext(DNode<T> next) {
    this.next = next;
  }

  // Getters
  public DNode<T> getPrev() {
    return prev;
  }

  public DNode<T> getNext() {
    return next;
  }

  public T getData() {
    return data;
  }
}
