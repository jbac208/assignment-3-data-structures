package nz.ac.auckland.se281.datastructures;

/**
 * A stack is a collection of elements with LIFO policy.
 *
 * @param <T> The type of elements stored in the stack.
 */
public interface Stack<T> {

  /**
   * Returns number of elements in the stack.
   *
   * @return Size of the stack.
   */
  public int size();

  /**
   * Checks if the stack is empty or not.
   *
   * @return true if the stack is empty, false otherwise.
   */
  public boolean isEmpty();

  /**
   * Retrieves element at top of the stack without removing it.
   *
   * @return The element at the top of the stack.
   */
  public T peek();

  /**
   * Adds an element to the top of the stack.
   *
   * @param element The element to be added.
   */
  public void push(T element);

  /**
   * Removes and returns the element at the top of the stack.
   *
   * @return The element at the top of the stack.
   */
  public T pop();
}
