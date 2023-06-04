package nz.ac.auckland.se281.datastructures;

public interface Queue<T> {

  public int size();

  public boolean isEmpty();

  public T front() throws InvalidPositionException;

  public void enqueue(T element) throws InvalidPositionException;

  public T dqueue() throws InvalidPositionException;
}
