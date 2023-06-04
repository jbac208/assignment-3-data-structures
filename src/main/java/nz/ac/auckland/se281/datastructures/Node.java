package nz.ac.auckland.se281.datastructures;

import java.util.List;
import java.util.ArrayList;

public class Node<T> {
  private T val;
  private Node<T> next;
  private List<Node<T>> neighbours = new ArrayList<Node<T>>();

  // constructor

  public Node() {}

  public Node(T v) {
    val = v;
    this.next = null;
    ;
  }

  public Node(T v, Node<T> next) {
    val = v;
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
