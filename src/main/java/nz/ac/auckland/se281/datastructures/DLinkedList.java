package nz.ac.auckland.se281.datastructures;

/**
 * Custom Doubly linked list data structure implementation.
 *
 * @param <T> The type of elements in the linked list.
 */
public class DLinkedList<T> {

  private DNode<T> head;
  private DNode<T> tail;

  /** Constructs empty doubly linked list. */
  public DLinkedList() {
    head = null;
    tail = null;
  }

  /**
   * Inserts a new node with the specified data at head of linked list.
   *
   * @param data The data to be inserted.
   */
  public void insertAtHead(T data) {
    // push
    DNode<T> nodeToBeInserted = new DNode<>(data);
    if (head == null) {
      tail = nodeToBeInserted;
    } else {
      head.setPrev(nodeToBeInserted);
    }
    nodeToBeInserted.setNext(head);
    head = nodeToBeInserted;
  }

  /**
   * Inserts a new node with the specified data at tail of linked list.
   *
   * @param data The data to be inserted.
   */
  public void insertAtTail(T data) {
    DNode<T> nodeToBeInserted = new DNode<>(data);
    if (tail == null) { // List is empty
      head = nodeToBeInserted;
      tail = nodeToBeInserted;
    } else {
      tail.setNext(nodeToBeInserted);
      nodeToBeInserted.setPrev(tail);
      tail = nodeToBeInserted;
    }
  }

  /** Deletes the node at head of linked list. */
  public void deleteHead() {
    if (head != null) {
      DNode<T> temp = head;
      head = temp.getNext();
      if (head != null) {
        head.setPrev(null);
      } else {
        tail = null; // List is now empty
      }
    }
  }

  /** Deletes the node at tail of linked list. */
  public void deleteTail() {
    if (tail != null) {
      DNode<T> temp = tail;
      tail = temp.getPrev();
      if (tail != null) {
        tail.setNext(null);
      } else {
        head = null; // List is now empty
      }
    }
  }

  // returning/getter methods

  /**
   * Returns number of elements in linked list.
   *
   * @return The size of the linked list.
   */
  public int size() {
    int count = 0;
    DNode<T> current = head;
    while (current != null) {
      ++count;
      current = current.getNext();
    }
    return count;
  }

  /**
   * Returns the head node of linked list.
   *
   * @return Head node.
   */
  public DNode<T> getHead() {
    // peek
    return head;
  }

  /**
   * Returns the tail node of linked list.
   *
   * @return Tail node.
   */
  public DNode<T> getTail() {
    return tail;
  }
}
