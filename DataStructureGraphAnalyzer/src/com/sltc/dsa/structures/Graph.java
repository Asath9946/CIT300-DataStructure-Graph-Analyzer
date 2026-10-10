package com.sltc.dsa.structures;

import com.sltc.dsa.util.OperationStats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * COMPONENT F - GRAPH (mandatory component)
 *
 * The graph is stored using an ADJACENCY LIST, which is the standard choice
 * for sparse graphs because it uses O(V + E) memory instead of the O(V^2)
 * required by an adjacency matrix. An adjacency matrix view is also provided
 * so the two representations can be compared during the demonstration.
 *
 * Supported operations
 *   - add vertex
 *   - add edge (undirected by default, directed optionally)
 *   - remove vertex / remove edge
 *   - display adjacency list and adjacency matrix
 *   - BFS traversal (queue based)        O(V + E)
 *   - DFS traversal (stack based)        O(V + E)
 *   - path search between two vertices (BFS, gives the shortest hop path)
 *   - connectivity check
 */
public class Graph {

    /** Insertion order is preserved so traversal output is predictable. */
    private final Map<String, List<String>> adjacencyList = new LinkedHashMap<>();
    private boolean directed;

    public Graph() {
        this(false);
    }

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        int total = 0;
        for (List<String> neighbours : adjacencyList.values()) {
            total += neighbours.size();
        }
        return directed ? total : total / 2;
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    public boolean hasVertex(String vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }

    /** Adds a vertex. O(1). */
    public OperationStats addVertex(String vertex) {
        long start = System.nanoTime();
        long steps = 1;

        if (adjacencyList.containsKey(vertex)) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Vertex '" + vertex + "' already exists.");
        }
        adjacencyList.put(vertex, new ArrayList<>());
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Vertex '" + vertex + "' added. Total vertices = " + adjacencyList.size() + ".");
    }

    /**
     * Adds an edge between two vertices. Missing vertices are created
     * automatically so the demo flows smoothly. O(1) amortised.
     */
    public OperationStats addEdge(String source, String destination) {
        long start = System.nanoTime();
        long steps = 0;

        if (source.equals(destination)) {
            return new OperationStats(++steps, System.nanoTime() - start, false,
                    "Self-loop rejected: source and destination are both '" + source + "'.");
        }

        boolean createdSource = false;
        boolean createdDestination = false;

        if (!adjacencyList.containsKey(source)) {
            adjacencyList.put(source, new ArrayList<>());
            createdSource = true;
        }
        steps++;
        if (!adjacencyList.containsKey(destination)) {
            adjacencyList.put(destination, new ArrayList<>());
            createdDestination = true;
        }
        steps++;

        if (adjacencyList.get(source).contains(destination)) {
            return new OperationStats(++steps, System.nanoTime() - start, false,
                    "Edge " + source + " - " + destination + " already exists.");
        }

        adjacencyList.get(source).add(destination);
        steps++;
        if (!directed) {
            adjacencyList.get(destination).add(source);
            steps++;
        }

        String note = "";
        if (createdSource || createdDestination) {
            note = " (missing vertex created automatically)";
        }
        String arrow = directed ? " -> " : " - ";

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Edge " + source + arrow + destination + " added." + note);
    }

    /** Removes an edge. O(V). */
    public OperationStats removeEdge(String source, String destination) {
        long start = System.nanoTime();
        long steps = 2;

        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "One or both vertices do not exist.");
        }
        boolean removed = adjacencyList.get(source).remove(destination);
        steps++;
        if (!directed) {
            adjacencyList.get(destination).remove(source);
            steps++;
        }
        if (!removed) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "No edge exists between " + source + " and " + destination + ".");
        }
        return new OperationStats(steps, System.nanoTime() - start, true,
                "Edge " + source + " - " + destination + " removed.");
    }

    /** Removes a vertex and every edge attached to it. O(V + E). */
    public OperationStats removeVertex(String vertex) {
        long start = System.nanoTime();
        long steps = 1;

        if (!adjacencyList.containsKey(vertex)) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Vertex '" + vertex + "' does not exist.");
        }
        adjacencyList.remove(vertex);
        steps++;
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(vertex);
            steps++;
        }
        return new OperationStats(steps, System.nanoTime() - start, true,
                "Vertex '" + vertex + "' and all of its edges were removed.");
    }

    /** Prints the adjacency list. O(V + E). */
    public OperationStats displayAdjacencyList() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The graph is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Graph is empty.");
        }

        System.out.println("        Representation : ADJACENCY LIST");
        System.out.println("        Graph type     : " + (directed ? "DIRECTED" : "UNDIRECTED"));
        System.out.println();
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("        %-12s -> ", entry.getKey()));
            if (entry.getValue().isEmpty()) {
                sb.append("(no neighbours)");
            } else {
                for (String neighbour : entry.getValue()) {
                    sb.append(neighbour).append("  ");
                    steps++;
                }
            }
            System.out.println(sb);
            steps++;
        }
        System.out.println();
        System.out.println("        Vertices = " + getVertexCount() + " , Edges = " + getEdgeCount());

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed " + getVertexCount() + " vertices.");
    }

    /** Prints the same graph as an adjacency matrix for comparison. O(V^2). */
    public OperationStats displayAdjacencyMatrix() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The graph is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Graph is empty.");
        }

        List<String> vertices = new ArrayList<>(adjacencyList.keySet());
        System.out.println("        Representation : ADJACENCY MATRIX");
        System.out.print("        " + String.format("%-12s", ""));
        for (String v : vertices) {
            System.out.print(String.format("%-8s", v));
        }
        System.out.println();

        for (String row : vertices) {
            System.out.print("        " + String.format("%-12s", row));
            for (String col : vertices) {
                int value = adjacencyList.get(row).contains(col) ? 1 : 0;
                System.out.print(String.format("%-8d", value));
                steps++;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("        Memory note: the matrix always stores V x V = "
                + (vertices.size() * vertices.size()) + " cells, while the adjacency");
        System.out.println("        list stores only V + 2E = "
                + (getVertexCount() + 2 * getEdgeCount()) + " references for this graph.");

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed a " + vertices.size() + " x " + vertices.size() + " matrix.");
    }

    /**
     * BREADTH FIRST SEARCH - explores level by level using a QUEUE.
     * Complexity O(V + E). Visits every vertex reachable from the start.
     */
    public OperationStats bfs(String startVertex) {
        long start = System.nanoTime();
        long steps = 0;

        if (!adjacencyList.containsKey(startVertex)) {
            return new OperationStats(++steps, System.nanoTime() - start, false,
                    "Start vertex '" + startVertex + "' does not exist.");
        }

        List<String> visitedOrder = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();   // FIFO

        queue.addLast(startVertex);
        visited.add(startVertex);
        steps++;

        while (!queue.isEmpty()) {
            String current = queue.removeFirst();   // dequeue
            visitedOrder.add(current);
            steps++;

            for (String neighbour : adjacencyList.get(current)) {
                steps++;   // every edge is examined once
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.addLast(neighbour);   // enqueue
                }
            }
        }

        System.out.println("        BFS uses a QUEUE (FIFO) - nearest vertices first.");
        System.out.println("        Visit order : " + String.join(" -> ", visitedOrder));
        System.out.println("        Vertices visited : " + visitedOrder.size()
                + " of " + getVertexCount());

        return new OperationStats(steps, System.nanoTime() - start, true,
                "BFS from '" + startVertex + "' visited " + visitedOrder.size() + " vertices.");
    }

    /**
     * DEPTH FIRST SEARCH - explores as deep as possible using a STACK.
     * Complexity O(V + E).
     */
    public OperationStats dfs(String startVertex) {
        long start = System.nanoTime();
        long steps = 0;

        if (!adjacencyList.containsKey(startVertex)) {
            return new OperationStats(++steps, System.nanoTime() - start, false,
                    "Start vertex '" + startVertex + "' does not exist.");
        }

        List<String> visitedOrder = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        LinkedList<String> stack = new LinkedList<>();   // LIFO

        stack.addLast(startVertex);
        steps++;

        while (!stack.isEmpty()) {
            String current = stack.removeLast();   // pop
            steps++;
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            visitedOrder.add(current);

            List<String> neighbours = adjacencyList.get(current);
            // Push in reverse so the first neighbour is explored first.
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                steps++;
                if (!visited.contains(neighbours.get(i))) {
                    stack.addLast(neighbours.get(i));   // push
                }
            }
        }

        System.out.println("        DFS uses a STACK (LIFO) - one branch fully, then backtrack.");
        System.out.println("        Visit order : " + String.join(" -> ", visitedOrder));
        System.out.println("        Vertices visited : " + visitedOrder.size()
                + " of " + getVertexCount());

        return new OperationStats(steps, System.nanoTime() - start, true,
                "DFS from '" + startVertex + "' visited " + visitedOrder.size() + " vertices.");
    }

    /**
     * Recursive DFS, provided so the demonstration can show that the
     * iterative and recursive forms produce the same traversal logic.
     */
    public OperationStats dfsRecursive(String startVertex) {
        long start = System.nanoTime();
        if (!adjacencyList.containsKey(startVertex)) {
            return new OperationStats(1, System.nanoTime() - start, false,
                    "Start vertex '" + startVertex + "' does not exist.");
        }
        List<String> visited = new ArrayList<>();
        long[] steps = new long[1];
        dfsVisit(startVertex, visited, steps);

        System.out.println("        Recursive DFS (the call stack replaces the explicit stack)");
        System.out.println("        Visit order : " + String.join(" -> ", visited));

        return new OperationStats(steps[0], System.nanoTime() - start, true,
                "Recursive DFS visited " + visited.size() + " vertices.");
    }

    private void dfsVisit(String vertex, List<String> visited, long[] steps) {
        visited.add(vertex);
        steps[0]++;
        for (String neighbour : adjacencyList.get(vertex)) {
            steps[0]++;
            if (!visited.contains(neighbour)) {
                dfsVisit(neighbour, visited, steps);
            }
        }
    }

    /**
     * Searches for a path between two vertices using BFS, which guarantees
     * the path with the fewest edges in an unweighted graph. O(V + E).
     */
    public OperationStats findPath(String source, String destination) {
        long start = System.nanoTime();
        long steps = 0;

        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            return new OperationStats(++steps, System.nanoTime() - start, false,
                    "Both vertices must exist in the graph.");
        }

        Map<String, String> parent = new LinkedHashMap<>();
        List<String> visited = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();

        queue.addLast(source);
        visited.add(source);
        parent.put(source, null);
        steps++;

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            String current = queue.removeFirst();
            steps++;
            for (String neighbour : adjacencyList.get(current)) {
                steps++;
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    parent.put(neighbour, current);
                    if (neighbour.equals(destination)) {
                        found = true;
                        break;
                    }
                    queue.addLast(neighbour);
                }
            }
        }

        if (!found) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "No path exists between " + source + " and " + destination + ".");
        }

        LinkedList<String> path = new LinkedList<>();
        String node = destination;
        while (node != null) {
            path.addFirst(node);
            node = parent.get(node);
            steps++;
        }

        System.out.println("        Shortest path (fewest edges) : " + String.join(" -> ", path));
        System.out.println("        Number of hops : " + (path.size() - 1));

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Path found with " + (path.size() - 1) + " hop(s).");
    }

    /** Reports whether every vertex can be reached from the first vertex. */
    public OperationStats checkConnectivity() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            return new OperationStats(0, System.nanoTime() - start, false, "Graph is empty.");
        }

        String first = adjacencyList.keySet().iterator().next();
        List<String> visited = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(first);
        visited.add(first);

        while (!queue.isEmpty()) {
            String current = queue.removeFirst();
            steps++;
            for (String neighbour : adjacencyList.get(current)) {
                steps++;
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.addLast(neighbour);
                }
            }
        }

        boolean connected = visited.size() == getVertexCount();
        String message = connected
                ? "The graph is CONNECTED - all " + getVertexCount() + " vertices are reachable."
                : "The graph is DISCONNECTED - only " + visited.size() + " of "
                  + getVertexCount() + " vertices are reachable from '" + first + "'.";
        System.out.println("        " + message);

        return new OperationStats(steps, System.nanoTime() - start, connected, message);
    }

    /**
     * Loads a sample graph of Sri Lankan cities so the demonstration has
     * meaningful data instead of abstract letters.
     */
    public void loadSampleData() {
        adjacencyList.clear();
        directed = false;
        addEdge("Colombo", "Kandy");
        addEdge("Colombo", "Galle");
        addEdge("Colombo", "Negombo");
        addEdge("Kandy", "Badulla");
        addEdge("Kandy", "Ampara");
        addEdge("Galle", "Matara");
        addEdge("Badulla", "ArugamBay");
        addEdge("Ampara", "ArugamBay");
        addEdge("Negombo", "Kurunegala");
    }

    public void clear() {
        adjacencyList.clear();
    }
}
