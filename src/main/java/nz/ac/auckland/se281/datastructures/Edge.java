package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {

  // declare variables
  private T source;
  private T destination;

  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  public T getSource() {
    return source;
  }

  public T getDestination() {
    return destination;
  }

  // @Override
  // public boolean equals(Object obj) {
  //   if (!(obj instanceof Edge)) {
  //     return false;
  //   }

  //   Edge<T> otherEdge = (Edge<T>) obj;

  //   // Compare the source and destination vertices for equality
  //   if (source != null
  //       && source.equals(otherEdge.getSource())
  //       && destination.equals(otherEdge.getDestination())) {
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }
}
