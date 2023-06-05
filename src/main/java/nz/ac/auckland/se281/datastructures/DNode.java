package nz.ac.auckland.se281.datastructures;

/**
 * A node in a doubly linked list.
 *
 * @param <T> The type of data stored in the node.
 */
public class DNode<T> {

  private T data;
  private DNode<T> prev;
  private DNode<T> next;

  /** Constructs an empty DNode. */
  public DNode() {}

  /**
   * Constructs a DNode with the specified data.
   *
   * @param data The data to be stored in the node.
   */
  public DNode(T data) {
    this.data = data;
    prev = null;
    next = null;
  }

  /**
   * Constructs a DNode with the specified data and previous node.
   *
   * @param data The data to be stored in the node.
   * @param prev The previous node.
   */
  public DNode(T data, DNode<T> prev) {
    this.data = data;
    this.prev = prev;
    next = null;
  }

  /**
   * Constructs a DNode with the specified data, previous node, and next node.
   *
   * @param data The data to be stored in the node.
   * @param prev The previous node.
   * @param next The next node.
   */
  public DNode(T data, DNode<T> prev, DNode<T> next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  // Setters

  /**
   * Sets the previous node.
   *
   * @param prev The previous node.
   */
  public void setPrev(DNode<T> prev) {
    this.prev = prev;
  }

  /**
   * Sets the next node.
   *
   * @param next The next node.
   */
  public void setNext(DNode<T> next) {
    this.next = next;
  }

  // Getters

  /**
   * Returns the previous node.
   *
   * @return The previous node.
   */
  public DNode<T> getPrev() {
    return prev;
  }

  /**
   * Returns the next node.
   *
   * @return The next node.
   */
  public DNode<T> getNext() {
    return next;
  }

  /**
   * Returns the data stored in the node.
   *
   * @return The data stored in the node.
   */
  public T getData() {
    return data;
  }
}
