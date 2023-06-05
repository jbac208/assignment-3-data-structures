package nz.ac.auckland.se281.datastructures;

/**
 * A node in a doubly linked list.
 *
 * @param <T> The type of data stored in the node.
 */
public class Dnode<T> {

  private T data;
  private Dnode<T> prev;
  private Dnode<T> next;

  /** Constructs an empty Dnode. */
  public Dnode() {}

  /**
   * Constructs a Dnode with the specified data.
   *
   * @param data The data to be stored in the node.
   */
  public Dnode(T data) {
    this.data = data;
    prev = null;
    next = null;
  }

  /**
   * Constructs a Dnode with the specified data and previous node.
   *
   * @param data The data to be stored in the node.
   * @param prev The previous node.
   */
  public Dnode(T data, Dnode<T> prev) {
    this.data = data;
    this.prev = prev;
    next = null;
  }

  /**
   * Constructs a Dnode with the specified data, previous node, and next node.
   *
   * @param data The data to be stored in the node.
   * @param prev The previous node.
   * @param next The next node.
   */
  public Dnode(T data, Dnode<T> prev, Dnode<T> next) {
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
  public void setPrev(Dnode<T> prev) {
    this.prev = prev;
  }

  /**
   * Sets the next node.
   *
   * @param next The next node.
   */
  public void setNext(Dnode<T> next) {
    this.next = next;
  }

  // Getters

  /**
   * Returns the previous node.
   *
   * @return The previous node.
   */
  public Dnode<T> getPrev() {
    return prev;
  }

  /**
   * Returns the next node.
   *
   * @return The next node.
   */
  public Dnode<T> getNext() {
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
