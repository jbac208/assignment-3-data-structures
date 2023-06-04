package nz.ac.auckland.se281.datastructures;

public class InvalidPositionException extends Exception {

  /** */
  private static final long serialVersionUID = 1L;

  public InvalidPositionException() {}

  public InvalidPositionException(String msg) {
    super(msg);
  }
}
