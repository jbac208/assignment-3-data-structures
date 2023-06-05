package nz.ac.auckland.se281.datastructures;

/**
 * Custom queue data structure using a doubly linked list.
 *
 * @param <T> The type of elements stored in the queue.
 */
public class DlinkedListQueue<T> implements Queue<T> {

  protected DlinkedList<T> queueData;

  /** Constructs an empty DLinkedListQueue. */
  public DlinkedListQueue() {
    this.queueData = new DlinkedList<>();
  }

  /**
   * Returns number of elements in the queue.
   *
   * @return The size of the queue.
   */
  @Override
  public int size() {
    return queueData.size();
  }

  /**
   * Checks if queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return queueData.size() == 0;
  }

  /**
   * Returns the element at the front of the queue without removing it.
   *
   * @return The element at the front of the queue.
   */
  @Override
  public T front() {
    return queueData.getHead().getData();
  }

  /**
   * Adds an element to the back of the queue.
   *
   * @param element The element to be added to the queue.
   */
  @Override
  public void enqueue(T element) {
    queueData.insertAtTail(element);
  }

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return The element at the front of the queue.
   */
  @Override
  public T dqueue() {
    T frontData = front();
    queueData.deleteHead();
    return frontData;
  }
}
