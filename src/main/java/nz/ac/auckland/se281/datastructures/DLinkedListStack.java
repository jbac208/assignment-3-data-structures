package nz.ac.auckland.se281.datastructures;

public class DLinkedListStack<T> implements Stack<T> {

  protected DLinkedList<T> stackData;
  protected int top = -1;

  public DLinkedListStack() {
    this.stackData = new DLinkedList<>();
  }

  @Override
  public int size() {
    return stackData.size();
  }

  @Override
  public boolean isEmpty() {
    return stackData.size() == 0;
  }

  @Override
  public T peek() {
    return stackData.getHead().getData();
  }

  @Override
  public void push(T element) {
    stackData.insertAtHead(element);
  }

  @Override
  public T pop() {
    T headData = peek();
    stackData.deleteHead();
    return headData;
  }
}
