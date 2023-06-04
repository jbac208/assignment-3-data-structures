package nz.ac.auckland.se281.datastructures;

/**
 * The Linked List Class Has only one head pointer to the start node Nodes are indexed starting from
 * 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class LinkedList<T> implements List<T> {
  private Node<T> head;

  public LinkedList() {
    head = null;
  }

  // Key methods of the List interface

  /**
   * This method adds a node with specified data as the start node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void prepend(T data) {
    // Note -- works even if list is empty
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void append(T data) {
    if (head == null) {
      prepend(data);
    }

    Node<T> newNode = new Node<T>(data);
    Node<T> current = head;
    while (current.getNext() != null) {
      current = current.getNext();
    }
    current.setNext(newNode);
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public T fetch(int pos) throws InvalidPositionException {
    if (pos < 0 || pos >= size()) {
      throw new InvalidPositionException("Invalid position");
    }

    Node<T> current = head;
    for (int i = 0; i < pos; i++) {
      current = current.getNext();
    }
    return current.getValue();
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public void insert(int pos, T data) throws InvalidPositionException {
    if (pos < 0 || pos > size()) {
      throw new InvalidPositionException("Invalid position");
    }

    if (pos == 0) {
      prepend(data);
      return;
    }

    Node<T> newNode = new Node<T>(data);
    Node<T> current = head;
    for (int i = 0; i < pos - 1; i++) {
      current = current.getNext();
    }
    newNode.setNext(current.getNext());
    current.setNext(newNode);
  }

  /**
   * This method removes a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return void
   */
  public void remove(int pos) throws InvalidPositionException {
    if (pos < 0 || pos >= size()) {
      throw new InvalidPositionException("Invalid position");
    }

    if (pos == 0) {
      head = head.getNext();
      return;
    }

    Node<T> current = head;
    for (int i = 0; i < pos - 1; i++) {
      current = current.getNext();
    }
    current.setNext(current.getNext().getNext());
  }

  /**
   * This method returns the size of a list
   *
   * @param
   * @return the size of the list
   */
  public int size() {
    int count = 0;
    Node<T> current = head;
    while (current != null) {
      ++count;
      current = current.getNext();
    }
    return count;
  }
}
