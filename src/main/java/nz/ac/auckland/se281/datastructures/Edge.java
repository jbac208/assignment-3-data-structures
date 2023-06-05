package nz.ac.auckland.se281.datastructures;

import java.util.Objects;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T extends Comparable<T>> {

  // declare variables
  private T source;
  private T destination;

  /**
   * Constructs an edge with the given source and destination vertices.
   *
   * @param source The source vertex.
   * @param destination The destination vertex.
   */
  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * Returns source vertex of this edge.
   *
   * @return The source vertex.
   */
  public T getSource() {
    return source;
  }

  /**
   * Returns destination vertex of this edge.
   *
   * @return The destination vertex.
   */
  public T getDestination() {
    return destination;
  }

  /**
   * Compares this edge to the specified object for equality. Two edges are considered equal if
   * their source and destination vertices are equal.
   *
   * @param obj The object to compare.
   * @return return true if the edges are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Edge)) {
      return false;
    }

    Edge<?> otherEdge = (Edge<?>) obj;

    // Compare the source and destination vertices for equality
    if (source != null
        && source.equals(otherEdge.getSource())
        && destination.equals(otherEdge.getDestination())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the hash code value for this edge.
   *
   * @return The hash code of the edge.
   */
  @Override
  public int hashCode() {
    return Objects.hash(source);
  }
}
