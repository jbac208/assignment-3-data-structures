package nz.ac.auckland.se281.datastructures;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  // declare variables
  private Set<T> vertices;
  private Set<Edge<T>> edges;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.vertices = verticies;
    this.edges = edges;
  }

  public Set<Integer> getRoots() {
    Set<Integer> nRoots = new HashSet<>();
    Set<T> tRoots = new HashSet<>();

    // Check for roots with in-degree 0 and out-degree > 0
    for (T vertex : vertices) {
      boolean hasIncomingEdges = false;
      boolean hasOutgoingEdges = false;

      for (Edge<T> edge : edges) {
        if (edge.getDestination().equals(vertex)) {
          hasIncomingEdges = true;
        }
        if (edge.getSource().equals(vertex)) {
          hasOutgoingEdges = true;
        }
      }

      if (!hasIncomingEdges && hasOutgoingEdges) {
        tRoots.add(vertex);
        nRoots.add(Integer.parseInt((String) vertex));
      }
    }

    // Check for roots in equivalence classes
    Set<T> equivVertices = new HashSet<>();
    for (T vertex : vertices) {
      if (getEquivalenceClass(vertex).size() > 0 && !equivVertices.contains(vertex)) {
        tRoots.add(vertex);
        nRoots.add(Integer.parseInt((String) vertex));
        equivVertices = getEquivalenceClass(vertex);
      }
    }
    return nRoots;
  }

  private Set<Integer> setToIntSet(Set<T> set) {
    Set<String> stringSet = new HashSet<>();
    for (T element : set) {
      stringSet.add((String) element);
    }

    Set<Integer> intSet = new HashSet<>();
    for (String element : stringSet) {
      intSet.add(Integer.parseInt(element));
    }
    return intSet;
  }

  public boolean isReflexive() {
    for (T vertex : vertices) {
      // define the reflexive edge for that vertex
      Edge<T> reflexiveEdge = new Edge<>(vertex, vertex);

      if (!edges.contains(reflexiveEdge)) {
        return false;
      }
    }
    return true;
  }

  public boolean isSymmetric() {
    // symmetric -> all edges are bidirectional
    for (Edge<T> edge : edges) {
      // define symmetric edge
      Edge<T> reverseEdge = new Edge<>(edge.getDestination(), edge.getSource());
      if (!edges.contains(reverseEdge)) {
        return false;
      }
    }
    // we reached end of vertices so we have symmetry
    return true;
  }

  public boolean isTransitive() {
    // check transitivity
    return isSetTransitive(edges);
  }

  private boolean isSetTransitive(Set<Edge<T>> edges) {
    // check transitivity
    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getDestination().equals(edge2.getSource())) {
          Edge<T> transitiveEdge = new Edge<>(edge1.getSource(), edge2.getDestination());
          if (!edges.contains(transitiveEdge)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isAntiSymmetric() {
    // check isAntiSymmetric
    for (Edge<T> edge : edges) {
      Edge<T> reverseEdge = new Edge<>(edge.getDestination(), edge.getSource());
      if (edges.contains(reverseEdge) && !edge.getSource().equals(edge.getDestination()))
        return false;
    }
    return true;
  }

  public boolean isEquivalence() {
    // equivalence check
    return isReflexive() && isSymmetric() && isTransitive();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> equivalenceClassSet = new HashSet<>();
    T nextV = null;
    Set<T> visited = new HashSet<>();

    if (isEquivalence()) {
      equivalenceClassSet.add(vertex);
      visited.add(vertex);

      while (!visited.contains(nextV)) {
        for (Edge<T> edge : edges) {
          if (vertex.equals(edge.getSource())) {
            nextV = edge.getDestination();
            if (!visited.contains(nextV)) {
              visited.add(nextV);
              equivalenceClassSet.add(nextV);
            }
          }
        }
      }
    }
    return equivalenceClassSet;
  }

  private Stack<T> verticesToDLinkedList() {
    // convert the vertices set into dnodes in a dlinkedlist
    Stack<T> convertedList = new DLinkedListStack<>();
    for (T vertex : vertices) {
      convertedList.push(vertex);
    }
    return convertedList;
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    Stack<DNode<T>> stack = new DLinkedListStack<>();
    Stack<T> convertedList = verticesToDLinkedList();
    T vTemp = convertedList.peek();
    stack.push(vTemp);
    System.out.println(convertedList.peek());
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
