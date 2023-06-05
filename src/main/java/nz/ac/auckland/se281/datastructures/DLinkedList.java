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
    if (tail == null) { // List is empty
      head = nodeToBeInserted;
      tail = nodeToBeInserted;
    } else {
      tail.setNext(nodeToBeInserted);
      nodeToBeInserted.setPrev(tail);
      tail = nodeToBeInserted;
    }
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
