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
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public T fetch(int pos) {
    T val = head.getValue();

    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public void insert(int pos, T data) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * This method removes a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return void
   */
  public void remove(int pos) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * This method returns the size of a list
   *
   * @param
   * @return the size of the list
   */
  public int size() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
