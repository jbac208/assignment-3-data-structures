package nz.ac.auckland.se281.datastructures;

public class LinkedListStack<T> implements Stack<T> {

  protected List<T> stackData;
  protected int top = -1;

    public LinkedListStack() {
        this.stackData = new LinkedList<>();
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
    return stackData.fetch(top);
  }

  @Override
  public void push(T element) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'push'");
  }

  @Override
  public T pop() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pop'");
  }
}
