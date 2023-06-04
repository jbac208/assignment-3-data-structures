package nz.ac.auckland.se281.datastructures;

import java.util.Collections;
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
    Set<Integer> roots = new HashSet<>();
    Set<T> rootRemovedVertices = new HashSet<>(vertices);

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
        rootRemovedVertices.remove(vertex);
        roots.add(Integer.parseInt((String) vertex));
      }
    }

    // Check for roots in equivalence classes
    Graph<T> rootRemovedGraph = new Graph<>(rootRemovedVertices, edges);
    if (rootRemovedGraph.isEquivalence()) {
      Set<Integer> equivalenceClasses = new HashSet<>();
      for (T vertex : vertices) {
        equivalenceClasses.addAll(setToIntSet(getEquivalenceClass(vertex)));
      }
      roots.add(Collections.min(equivalenceClasses));
    }
    return roots;
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
    if (isEquivalence()) {
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex)) {
          // if edge source is the given vertex, add to equivalence class
          equivalenceClassSet.add(edge.getDestination());
        }
      }

      int prevSize = equivalenceClassSet.size();
      do {
        prevSize = equivalenceClassSet.size();
        Set<T> tempSet = new HashSet<>(equivalenceClassSet);
        for (T v : equivalenceClassSet) { // where v is vertex in eq class set
          for (Edge<T> edge : edges) {
            if (edge.getSource().equals(v)) {
              tempSet.add(edge.getDestination());
            }
          }
        }
        equivalenceClassSet = tempSet;
      } while ((prevSize != equivalenceClassSet.size()));
    }
    return equivalenceClassSet;
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
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
