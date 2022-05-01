import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Utility class providing some basic graph algorithms. (Based on an earlier lab
 * designed by John Bowers.)
 *
 * @author Nathan Sprague and ??
 */

public class GraphAlgorithms {

  /**
   * Return the out degree of vertex v.
   */
  public static <T> int getOutDegreeOfVertex(Graph<T> graph, T vertex) {
    int degrees = 0;
    for (T node : graph.neighbors(vertex)) {
      degrees++;
    }
    return degrees;
  }

  /**
   * Return the in degree of vertex v.
   */
  public static <T> int getInDegreeOfVertex(Graph<T> graph, T vertex) {
    int degrees = 0;
    for (T node : graph.allNodes()) {
      if (graph.hasEdge(node, vertex)) {
        degrees++;
      }
    }
    return degrees;
  }


  /**
   * Returns true if the graph is connected. (This method should only be applied
   * to undirected graphs.)
   */
  public static <T> boolean isConnected(Graph<T> graph) {
    // FIRST IMPLEMENT THE DFS TRAVERSAL BELOW.

    // A graph is considered "connected" if every node is reachable from every other
    // node. We can check for connectedness by starting a traversal at an arbitrary
    // node. if all nodes are visited, then the graph was connected. If there are
    // any unvisited nodes then the graph was not connected.
    T[] nodes = (T[]) graph.allNodes().toArray();
    HashMap<T, Boolean> visited = dfSearch(graph, nodes[0]);
    Boolean[] visited2 =
      visited.values().toArray(new Boolean[visited.size()]);
    for (Boolean bool : visited2) {
      if (!bool) {
        return false;
      }
    }
    return true;
  }

  /**
   * Perform a DFS traversal of the provided graph starting at the indicated
   * vertex. This method returns a HashMap indicating which nodes in the graph
   * were visited during the traversal. Vertices that were visited will be mapped
   * to true.
   *
   * @param graph  The graph to traverse
   * @param vertex The starting vertex
   * @return A map indicating which vertices were visited
   */
  public static <T> HashMap<T, Boolean> dfSearch(Graph<T> graph, T vertex) {

    // CREATE THE HASH MAP HERE.
    // INITIALIZE ALL VERTICES TO FALSE.
    // THE ACTUAL TRAVERSAL WILL BE HANDLED IN THE HELPER.
    HashMap<T, Boolean> visited = new HashMap<>();
    // Sets all nodes to false
    for (T node : graph.allNodes()) {
      visited.put(node, false);
    }
    dfsHelper(graph, vertex, visited);
    return visited;
  }

  /**
   * Recursive DFS traversal.
   */
  private static <T> void dfsHelper(Graph<T> graph, T vertex, HashMap<T, Boolean> visited) {

    // STEPS:
    // - MARK THE CURRENT VERTEX AS VISITED
    // - RECURSIVELY TRAVERSE ALL NON-VISITED NEIGHBORS
    visited.put(vertex, true);
    // recursively calls all non-visited neighbors
    for (T node : graph.neighbors(vertex)) {
      if (!visited.get(node)) {
        dfsHelper(graph, node, visited);
      }
    }
  }
}
