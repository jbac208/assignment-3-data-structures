// unused atm

package nz.ac.auckland.se281.datastructures;

/**
 * The Linked List Class Has only one head pointer to the start node Nodes are indexed starting from
 * 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class LinkedList<T> implements List<T> {
  protected Node<T> head;

  public LinkedList() {
    head = null;
  }

  public Node<T> getHead() {
    return this.head;
  }

  // Key methods of the List interface

  public Node<T> locateNode(int pos) throws InvalidPositionException {
    if (pos < 0 || pos > size() - 1) {
      throw new InvalidPositionException("Invalid Position");
    }
    Node<T> current = head;
    int i = 0;
    while (current != null) {
      if (i < pos) {
        current = current.getNext();
        ++i;
      } else {
        return current;
      }
    }
    return null;
  }

  /**
   * This method adds a node with specified data as the start node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void prepend(T data) {
    // Note -- works even if list is empty
    Node<T> n = new Node<T>(data, head);
    head = n;
  }

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void append(T data) {
    Node<T> current;
    current = head;

    if (current == null) {
      Node<T> n = new Node<T>(data);
      head = n;
    } else {
      try {
        current = locateNode(size() - 1);
        Node<T> n = new Node<T>(data, null);
        current.setNext(n);
      } catch (InvalidPositionException e) {
        System.out.println("Locating at invalid position");
      }
    }
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public T fetch(int pos) throws InvalidPositionException {
    if (pos < 0 || pos >= size() - 1) {
      throw new InvalidPositionException("Invalid position");
    }
    Node<T> current = locateNode(pos);
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
    } else if (pos == 0) {
      prepend(data);
    } else if (pos == size()) {
      append(data);
    } else {
      Node<T> current = locateNode(pos - 1);
      Node<T> after = current.getNext();
      Node<T> newN = new Node<T>(data, after);
      current.setNext(newN);
    }
  }

  /**
   * This method removes a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return void
   */
  public void remove(int pos) throws InvalidPositionException {
    if (pos < 0 || pos >= size() - 1) {
      throw new InvalidPositionException("Invalid position");
    } else if (pos == 0) {
      head = head.getNext();
    } else {
      Node<T> current = locateNode(pos - 1);
      Node<T> after = current.getNext();
      Node<T> newAfter = after.getNext();
      current.setNext(newAfter);
    }
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
