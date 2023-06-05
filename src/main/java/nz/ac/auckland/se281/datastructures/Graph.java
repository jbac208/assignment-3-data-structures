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

  /**
   * Constructs a graph with the given vertices and edges.
   *
   * @param vertices The set of vertices in the graph.
   * @param edges The set of edges in the graph.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.vertices = verticies;
    this.edges = edges;
  }

  /**
   * Returns the roots of this graph, i.e., the vertices with in-degree 0 and out-degree > 0. The
   * roots are returned in ascending order based on their numeric value.
   *
   * @return The set of roots in the graph.
   */
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

    // return sorted set
    return orderHashSet(tRoots);
  }

  /**
   * Orders a set of elements in ascending order based on their numeric value.
   *
   * @param set The set to be ordered.
   * @return The ordered set.
   */
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

  /**
   * Checks if this graph is reflexive.
   *
   * @return true if the graph is reflexive, false otherwise.
   */
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

  /**
   * Checks if this graph is symmetric.
   *
   * @return true if the graph is symmetric, false otherwise.
   */
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

  /**
   * Checks if this graph is transitive.
   *
   * @return true if the graph is transitive, false otherwise.
   */
  public boolean isTransitive() {
    // check transivity
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

  /**
   * Checks if this graph is anti-symmetric.
   *
   * @return true if the graph is anti-symmetric, false otherwise.
   */
  public boolean isAntiSymmetric() {
    // check isAntiSymmetric
    for (Edge<T> edge : edges) {
      Edge<T> reverseEdge = new Edge<>(edge.getDestination(), edge.getSource());
      if (edges.contains(reverseEdge) && !edge.getSource().equals(edge.getDestination())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if this graph is an equivalence relation.
   *
   * @return true if the graph is an equivalence relation, false otherwise.
   */
  public boolean isEquivalence() {
    // equivalence check
    return isReflexive() && isSymmetric() && isTransitive();
  }

  /**
   * Retrieves the equivalence class of a given vertex.
   *
   * @param vertex The vertex for which to retrieve the equivalence class.
   * @return The set of vertices in the equivalence class.
   */
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

  /**
   * Retrieves the set of destination nodes for a given node.
   *
   * @param node The node for which to retrieve the destinations.
   * @return The set of destination nodes.
   */
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

  /**
   * Sorts a list of elements in ascending order based on their numeric value.
   *
   * @param list The list to be sorted.
   * @return The sorted list.
   */
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

  /**
   * Performs an iterative BFS on this graph.
   *
   * @return The list of visited vertices in the order they were visited.
   */
  public List<T> iterativeBreadthFirstSearch() {
    DlinkedListQueue<T> queue = new DlinkedListQueue<>();
    List<T> visited = new ArrayList<>();

    // Start at root and search from every root
    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      if (!visited.contains(root)) {
        queue.enqueue(root);
        visited.add(root);
      }

      while (!queue.isEmpty()) {
        T current = queue.dqueue();

        // Get neighbors and add them to the end of the queue
        List<T> neighbours = new ArrayList<>();
        for (T neighbour : setOfDestinations(current)) {
          if (!visited.contains(neighbour)) {
            neighbours.add(neighbour);
          }
        }

        // queue and add to visited in ascending numerical order
        List<T> sortedNeighbours = new ArrayList<>(bubbleSort(neighbours));
        for (T node : sortedNeighbours) {
          queue.enqueue(node);
          visited.add(node);
        }
      }
    }

    return visited;
  }

  /**
   * Performs an iterative DFS on this graph.
   *
   * @return The list of visited vertices in the order they were visited.
   */
  public List<T> iterativeDepthFirstSearch() {
    Stack<T> stack = new DlinkedListStack<>();
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

  /**
   * Performs a recursive BFS on this graph.
   *
   * @return The list of visited vertices in the order they were visited.
   */
  public List<T> recursiveBreadthFirstSearch() {
    Queue<T> queue = new DlinkedListQueue<>();
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

  /**
   * Recursive helper method for recursiveBreadthFirstSearch().
   *
   * @param queue The queue used for traversal.
   * @param visited The list of visited vertices.
   */
  private void recursiveBFSHelper(Queue<T> queue, List<T> visited) {
    if (queue.isEmpty()) {
      return;
    }

    T current = queue.dqueue();

    List<T> neighbors = new ArrayList<>(setOfDestinations(current));
    neighbors.sort(
        new Comparator<T>() {
          @Override
          public int compare(T node1, T node2) {
            int value1 = Integer.parseInt(node1.toString());
            int value2 = Integer.parseInt(node2.toString());
            return Integer.compare(value1, value2);
          }
        });

    for (T neighbor : neighbors) {
      if (!visited.contains(neighbor)) {
        queue.enqueue(neighbor);
        visited.add(neighbor);
      }
    }

    recursiveBFSHelper(queue, visited);
  }

  /**
   * Performs a recursive DFS on this graph.
   *
   * @return The list of visited vertices in the order they were visited.
   */
  public List<T> recursiveDepthFirstSearch() {
    Stack<T> stack = new DlinkedListStack<>();
    List<T> totalVisited = new ArrayList<>();

    Set<T> rootSet = getRoots();
    for (T root : rootSet) {
      recursiveDFSHelper(root, stack, totalVisited);
    }

    return totalVisited;
  }

  /**
   * Recursive helper method for recursiveDepthFirstSearch().
   *
   * @param node The current node being visited.
   * @param stack The stack used for traversal.
   * @param totalVisited The list of visited vertices.
   */
  private void recursiveDFSHelper(T node, Stack<T> stack, List<T> totalVisited) {
    stack.push(node);

    while (!stack.isEmpty()) {
      T current = stack.pop();
      if (!totalVisited.contains(current)) {
        totalVisited.add(current);

        List<T> destinations = new ArrayList<>(setOfDestinations(current));
        destinations.sort(
            new Comparator<T>() {
              @Override
              public int compare(T node1, T node2) {
                int value1 = Integer.parseInt(node1.toString());
                int value2 = Integer.parseInt(node2.toString());
                return Integer.compare(value1, value2);
              }
            });

        for (int i = destinations.size() - 1; i >= 0; i--) {
          stack.push(destinations.get(i));
        }
      }
    }
  }
}
