package nz.ac.auckland.se281.datastructures;

public interface Stack<T> {

  int size();

  public boolean isEmpty();

  public T peek() throws InvalidPositionException;

  public void push(T element);

  public T pop() throws InvalidPositionException;
}
