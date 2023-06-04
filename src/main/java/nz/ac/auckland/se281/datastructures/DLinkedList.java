package nz.ac.auckland.se281.datastructures;

public class DLinkedList<T> {

  private DNode<T> head;
  private DNode<T> tail;

  public DLinkedList() {
    head = null;
    tail = null;
  }

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

  public void insertAtTail(T data) {
    DNode<T> nodeToBeInserted = new DNode<>(data);
    tail.setNext(nodeToBeInserted);
    nodeToBeInserted.setPrev(tail);
    this.tail = nodeToBeInserted;
  }

  public void insertAt(int position, T data) {
    DNode<T> nodeToBeInserted = new DNode<>(data);
    DNode<T> temp = head;
    for (int i = 0; i < position; i++) {
      temp = temp.getNext();
    }
    nodeToBeInserted.setPrev(temp);
    nodeToBeInserted.setNext(temp.getNext());
    temp.setNext(nodeToBeInserted);
    nodeToBeInserted.getNext().setPrev(nodeToBeInserted);
  }

  public void deleteHead() {
    DNode<T> temp = head;
    this.head = temp.getNext();
    try {
      this.head.setPrev(null);
    } catch (NullPointerException e) {

    }
  }

  public void deleteTail() {
    DNode<T> previous = tail.getPrev();
    previous.setNext(null);
    this.tail = previous;
  }

  public void deleteAt(int position) {
    DNode<T> temp = head;
    for (int i = 0; i < position; i++) {
      temp = temp.getNext();
    }
    temp.getPrev().setNext(temp.getNext());
    temp.getNext().setPrev(temp.getPrev());
  }

  // returning/getter methods

  public int size() {
    int count = 0;
    DNode<T> current = head;
    while (current != null) {
      ++count;
      current = current.getNext();
    }
    return count;
  }

  public DNode<T> getHead() {
    // peek
    return head;
  }

  public DNode<T> getTail() {
    return tail;
  }
}
