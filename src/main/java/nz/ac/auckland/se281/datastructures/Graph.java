package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
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

  public Set<T> getRoots() {
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

    // convert int set to T set
    tRoots.clear();
    for (Integer root : nRoots) {
      tRoots.add((T) Integer.toString(root));
    }

    return tRoots;
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
    // convert the vertices set into dnodes in a dlinkedlist (NOT ORDERED)
    Stack<T> convertedList = new DLinkedListStack<>();
    for (T vertex : vertices) {
      convertedList.push(vertex);
    }
    return convertedList;
  }

  private Set<T> setOfAdjacentNodes(T node) {
    Set<T> adjacentNodes = new HashSet<>();
    for (Edge<T> edge : edges) {
      T nextNode = edge.getDestination();
      T prevNode = edge.getSource();
      if (prevNode.equals(node) && !nextNode.equals(node) && nextNode != null) {
        // next node exists and is not self
        adjacentNodes.add(nextNode);
      } else if (nextNode.equals(node) && !prevNode.equals(node) && prevNode != null) {
        // prev node exists and is not self
        adjacentNodes.add(prevNode);
      }
    }
    return adjacentNodes;
  }

  public void bubbleSort(List<T> list) {
    int n = list.size();
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
      swapped = false;

      for (int j = 0; j < n - i - 1; j++) {
        if (list.get(j).compareTo(list.get(j + 1)) > 0) {
          // Swap elements
          T temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
          swapped = true;
        }
      }

      // If no two elements were swapped, the list is already sorted
      if (!swapped) {
        break;
      }
    }
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    Stack<T> stack = new DLinkedListStack<>();
    List<T> visitConverted = new ArrayList<>();

    // start at root and search at every root
    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      stack.push(root);

      // traverse
      Set<T> visited = new HashSet<>();
      while (!stack.isEmpty()) {
        T current = stack.pop();
        // if unknown node, visit it
        if (!visited.contains(current)) {
          visited.add(current);

          // add adjacent (neighbour) nodes to stack
          for (T neighbour : setOfAdjacentNodes(current)) {
            stack.push(neighbour);
          }
        }
      }
      // convert visited Set to ordered List
      visitConverted.addAll(visited);
    }

    //bubbleSort(visitConverted);

    return visitConverted;
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
