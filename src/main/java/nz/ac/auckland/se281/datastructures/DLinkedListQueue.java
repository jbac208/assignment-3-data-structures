package nz.ac.auckland.se281.datastructures;

public class DLinkedListQueue<T> implements Queue<T> {

  protected DLinkedList<T> queueData;

  public DLinkedListQueue() {
    this.queueData = new DLinkedList<>();
  }

  @Override
  public int size() {
    return queueData.size();
  }

  @Override
  public boolean isEmpty() {
    return queueData.size() == 0;
  }

  @Override
  public T front() {
    return queueData.getHead().getData();
  }

  @Override
  public void enqueue(T element) {
    queueData.insertAtTail(element);
  }

  @Override
  public T dqueue() {
    T frontData = front();
    queueData.deleteHead();
    return frontData;
  }
}
