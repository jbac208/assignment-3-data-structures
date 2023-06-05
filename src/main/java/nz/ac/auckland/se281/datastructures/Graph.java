package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    List<T> rootsList = new ArrayList<>();
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
        rootsList.add(vertex);
      }
    }

    // Check for roots in equivalence classes
    Set<T> equivVertices = new HashSet<>();
    for (T vertex : vertices) {
      if (getEquivalenceClass(vertex).size() > 0 && !equivVertices.contains(vertex)) {
        tRoots.add(vertex);
        rootsList.add(vertex);
        equivVertices = getEquivalenceClass(vertex);
      }
    }

    // Sort nRoots in ascending order
    return orderHashSet(tRoots);
  }

  public Set<T> orderHashSet(Set<T> set) {
    // Create a custom comparator to compare string-wrapped numbers as numbers
    Comparator<T> comparator =
        (s1, s2) -> {
          Integer n1 = Integer.parseInt((String) s1);
          Integer n2 = Integer.parseInt((String) s2);
          return n1.compareTo(n2);
        };

    // Create a TreeSet with the custom comparator to maintain the numeric order
    Set<T> orderedSet = new TreeSet<>(comparator);
    orderedSet.addAll(set);

    return orderedSet;
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

  private Set<T> setOfDestinations(T node) {
    Set<T> destinationNodes = new HashSet<>();
    for (Edge<T> edge : edges) {
      T nextNode = edge.getDestination();
      T prevNode = edge.getSource();
      if (prevNode.equals(node) && !nextNode.equals(node) && nextNode != null) {
        // next node exists and is not self
        destinationNodes.add(nextNode);
      }
    }
    return destinationNodes;
  }

  private List<T> bubbleSort(List<T> list) {
    int n = list.size();
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
      swapped = false;

      for (int j = 0; j < n - i - 1; j++) {
        if (Integer.parseInt((String) list.get(j)) > Integer.parseInt((String) list.get(j + 1))) {
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
    return list;
  }

  public List<T> iterativeBreadthFirstSearch() {
    Queue<T> queue = new DLinkedListQueue<>();
    List<T> visited = new ArrayList<>();

    // start at root and search at every root
    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      if (!visited.contains(root)) {
        queue.enqueue(root);
        visited.add(root);
      }

      while (!queue.isEmpty()) {
        T current = queue.dqueue();
        // get neighbors
        for (T neighbor : setOfDestinations(current)) {
          if (!visited.contains(neighbor)) {
            queue.enqueue(neighbor);
            visited.add(neighbor);
          }
        }
      }
    }
    return visited;
  }

  public List<T> iterativeDepthFirstSearch() {
    Stack<T> stack = new DLinkedListStack<>();
    List<T> totalVisited = new ArrayList<>();

    // start at root and search at every root
    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      stack.push(root);

      // traverse
      List<T> visited = new ArrayList<>();
      while (!stack.isEmpty()) {
        T current = stack.peek();
        // if unknown node, visit it
        if (!visited.contains(current)) {
          visited.add(current);

          // check
          List<T> destinations = new ArrayList<>();
          while (destinations.size() <= 0) {
            for (T destination : setOfDestinations(current)) {
              if (!visited.contains(destination)) {
                destinations.add(destination);
              }
            }
            if (destinations.size() <= 0) {
              // go back until there is a neighbour
              stack.pop();
              // break if stack empty
              if (stack.isEmpty()) {
                break;
              }
              current = stack.peek();
            }
          }
          if (!destinations.isEmpty()) {
            stack.push(bubbleSort(destinations).get(0));
          }
        }
      }
      totalVisited.addAll(visited);
    }
    return totalVisited;
  }

  public List<T> recursiveBreadthFirstSearch() {
    Queue<T> queue = new DLinkedListQueue<>();
    List<T> visited = new ArrayList<>();

    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      if (!visited.contains(root)) {
        queue.enqueue(root);
        visited.add(root);
        recursiveBFSHelper(queue, visited);
      }
    }

    return visited;
  }

  private void recursiveBFSHelper(Queue<T> queue, List<T> visited) {
    if (queue.isEmpty()) {
      return;
    }

    T current = queue.dqueue();

    for (T neighbor : setOfDestinations(current)) {
      if (!visited.contains(neighbor)) {
        queue.enqueue(neighbor);
        visited.add(neighbor);
      }
    }

    recursiveBFSHelper(queue, visited);
  }

  private void dfsUtil(T node, Set<T> visited, List<T> totalVisited) {
    visited.add(node);
    totalVisited.add(node);

    Set<T> destinations = setOfDestinations(node);
    for (T dest : destinations) {
      if (!visited.contains(dest)) {
        dfsUtil(dest, visited, totalVisited);
      }
    }
  }

  public List<T> recursiveDepthFirstSearch() {
    Stack<T> stack = new DLinkedListStack<>();
    List<T> totalVisited = new ArrayList<>();

    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      recursiveDFSHelper(root, stack, totalVisited);
    }

    return totalVisited;
  }

  private void recursiveDFSHelper(T node, Stack<T> stack, List<T> totalVisited) {
    stack.push(node);

    while (!stack.isEmpty()) {
      T current = stack.pop();
      if (!totalVisited.contains(current)) {
        totalVisited.add(current);
        List<T> destinations = new ArrayList<>();
        for (T destination : setOfDestinations(current)) {
          if (!totalVisited.contains(destination)) {
            destinations.add(destination);
          }
        }
        for (int i = destinations.size() - 1; i >= 0; i--) {
          stack.push(destinations.get(i));
        }
      }
    }
  }
}
