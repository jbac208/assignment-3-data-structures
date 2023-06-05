package nz.ac.auckland.se281.datastructures;

/**
 * Custom stack data structure using a doubly linked list.
 *
 * @param <T> The type of elements in the stack.
 */
public class DlinkedListStack<T> implements Stack<T> {

  protected DlinkedList<T> stackData;

  /** Constructs empty stack. */
  public DlinkedListStack() {
    this.stackData = new DlinkedList<>();
  }

  /**
   * Returns number of elements in stack.
   *
   * @return The size of the stack.
   */
  @Override
  public int size() {
    return stackData.size();
  }

  /**
   * Checks if stack is empty.
   *
   * @return return true if the stack is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return stackData.size() == 0;
  }

  /**
   * Returns element at the top of the stack without removing it.
   *
   * @return The element at the top of the stack.
   */
  @Override
  public T peek() {
    return stackData.getHead().getData();
  }

  /**
   * Adds an element to the top of the stack.
   *
   * @param element The element to be pushed onto the stack.
   */
  @Override
  public void push(T element) {
    stackData.insertAtHead(element);
  }

  /**
   * Removes and returns element at the top of the stack.
   *
   * @return Element at the top of the stack.
   */
  @Override
  public T pop() {
    T headData = peek();
    stackData.deleteHead();
    return headData;
  }
}
