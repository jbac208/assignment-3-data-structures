package nz.ac.auckland.se281.datastructures;

/**
 * A queue is a collection of elements with FIFO policy.
 *
 * @param <T> The type of elements stored in the queue.
 */
public interface Queue<T> {

  /**
   * Returns the number of elements in the queue.
   *
   * @return The size of the queue.
   */
  public int size();

  /**
   * Checks if the queue is empty or not.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty();

  /**
   * Retrieves element at the front of the queue without removing it.
   *
   * @return The element at the front of the queue.
   */
  public T front();

  /**
   * Adds an element to the back of the queue.
   *
   * @param element The element to be added.
   */
  public void enqueue(T element);

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return The element at the front of the queue.
   */
  public T dqueue();
}
