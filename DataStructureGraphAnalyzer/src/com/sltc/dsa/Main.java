package com.sltc.dsa;

import com.sltc.dsa.algorithms.SearchAlgorithms;
import com.sltc.dsa.structures.ArrayStructure;
import com.sltc.dsa.structures.Graph;
import com.sltc.dsa.structures.LinkedListStructure;
import com.sltc.dsa.structures.QueueStructure;
import com.sltc.dsa.structures.StackStructure;
import com.sltc.dsa.util.ConsoleUI;
import com.sltc.dsa.util.InputValidator;
import com.sltc.dsa.util.OperationStats;
import com.sltc.dsa.util.PerformanceTracker;

import java.util.Scanner;

/**
 * CIT300 - Data Structures and Algorithms
 * Graded Practical Assignment 2
 * DATA STRUCTURE AND GRAPH PERFORMANCE ANALYZER
 *
 * Main integration class. It owns one instance of every data structure,
 * one shared PerformanceTracker, and drives the console menu system.
 *
 * Design note: all structures are created here and passed to the menu
 * handlers, so every component operates on the SAME objects. This is what
 * makes the system a single integrated application rather than six
 * separate programs.
 */
public class Main {

    private static final int DEFAULT_CAPACITY = 20;

    private final InputValidator input;
    private final PerformanceTracker tracker = new PerformanceTracker();

    private final ArrayStructure array = new ArrayStructure(DEFAULT_CAPACITY);
    private final StackStructure stack = new StackStructure(DEFAULT_CAPACITY);
    private final QueueStructure queue = new QueueStructure(DEFAULT_CAPACITY);
    private final LinkedListStructure linkedList = new LinkedListStructure();
    private final Graph graph = new Graph(false);
    private final SearchAlgorithms searcher = new SearchAlgorithms();

    public Main(Scanner scanner) {
        this.input = new InputValidator(scanner);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main application = new Main(scanner);
        application.run();
        scanner.close();
    }

    /** Application entry point after construction. */
    public void run() {
        printWelcome();

        if (input.readYesNo("Load sample demonstration data into all structures?")) {
            loadAllSampleData();
            ConsoleUI.printSuccess("Sample data loaded into Array, Stack, Queue, Linked List and Graph.");
        } else {
            ConsoleUI.printInfo("Starting with empty structures.");
        }

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = input.readIntInRange("Enter your choice: ", 1, 9);
            switch (choice) {
                case 1 -> arrayMenu();
                case 2 -> stackMenu();
                case 3 -> queueMenu();
                case 4 -> linkedListMenu();
                case 5 -> searchMenu();
                case 6 -> graphMenu();
                case 7 -> performanceMenu();
                case 8 -> displayAllResults();
                case 9 -> {
                    running = false;
                    printGoodbye();
                }
                default -> ConsoleUI.printError("Unknown option.");
            }
        }
    }

    // =====================================================================
    //  WELCOME / MENUS
    // =====================================================================

    private void printWelcome() {
        ConsoleUI.printLine();
        System.out.println("      CIT300 - DATA STRUCTURES AND ALGORITHMS");
        System.out.println("      GRADED PRACTICAL ASSIGNMENT 2");
        System.out.println("      DATA STRUCTURE & GRAPH PERFORMANCE ANALYZER");
        System.out.println("      Sri Lanka Technology Campus (SLTC)");
        ConsoleUI.printLine();
        System.out.println();
    }

    private void printMainMenu() {
        ConsoleUI.printHeading("DATA STRUCTURE & GRAPH ANALYZER");
        System.out.println("  1. Array Operations");
        System.out.println("  2. Stack Operations");
        System.out.println("  3. Queue Operations");
        System.out.println("  4. Linked List Operations");
        System.out.println("  5. Searching Operations");
        System.out.println("  6. Graph Operations");
        System.out.println("  7. Performance Comparison");
        System.out.println("  8. Display All Results");
        System.out.println("  9. Exit");
        ConsoleUI.printLine();
    }

    private void printGoodbye() {
        ConsoleUI.printHeading("SESSION SUMMARY");
        System.out.println("  Operations recorded this session : " + tracker.size());
        System.out.println("  Array size      : " + array.getSize());
        System.out.println("  Stack size      : " + stack.getSize());
        System.out.println("  Queue size      : " + queue.getSize());
        System.out.println("  Linked list size: " + linkedList.getSize());
        System.out.println("  Graph           : " + graph.getVertexCount() + " vertices, "
                + graph.getEdgeCount() + " edges");
        ConsoleUI.printLine();
        System.out.println("  Thank you for using the Data Structure & Graph Analyzer.");
        ConsoleUI.printLine();
    }

    private void loadAllSampleData() {
        array.loadSampleData();
        stack.loadSampleData();
        queue.loadSampleData();
        linkedList.loadSampleData();
        graph.loadSampleData();
    }

    // =====================================================================
    //  1. ARRAY MENU
    // =====================================================================

    private void arrayMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("ARRAY OPERATIONS");
            System.out.println("  1. Insert at end");
            System.out.println("  2. Insert at index");
            System.out.println("  3. Delete by index");
            System.out.println("  4. Delete by value");
            System.out.println("  5. Search (linear)");
            System.out.println("  6. Update a value");
            System.out.println("  7. Display array");
            System.out.println("  8. Load sample data");
            System.out.println("  9. Clear array");
            System.out.println(" 10. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 10);
            OperationStats stats = null;
            String label = null;

            switch (choice) {
                case 1 -> {
                    int value = input.readInt("Enter the value to insert: ");
                    stats = array.insertAtEnd(value);
                    label = "Array Insert (end)";
                }
                case 2 -> {
                    int index = input.readInt("Enter the index: ");
                    int value = input.readInt("Enter the value: ");
                    stats = array.insertAtIndex(index, value);
                    label = "Array Insert (index)";
                }
                case 3 -> {
                    int index = input.readInt("Enter the index to delete: ");
                    stats = array.deleteAtIndex(index);
                    label = "Array Delete (index)";
                }
                case 4 -> {
                    int value = input.readInt("Enter the value to delete: ");
                    stats = array.deleteByValue(value);
                    label = "Array Delete (value)";
                }
                case 5 -> {
                    int value = input.readInt("Enter the value to search for: ");
                    stats = array.search(value);
                    label = "Array Search";
                }
                case 6 -> {
                    int index = input.readInt("Enter the index to update: ");
                    int value = input.readInt("Enter the new value: ");
                    stats = array.update(index, value);
                    label = "Array Update";
                }
                case 7 -> {
                    ConsoleUI.printSubHeading("Current array");
                    stats = array.display();
                    label = "Array Display";
                }
                case 8 -> {
                    array.loadSampleData();
                    ConsoleUI.printSuccess("Sample data loaded into the array.");
                    array.display();
                }
                case 9 -> {
                    array.clear();
                    ConsoleUI.printSuccess("Array cleared.");
                }
                case 10 -> back = true;
            }

            report(stats, label, "Array");
            if (!back) {
                input.pause();
            }
        }
    }

    // =====================================================================
    //  2. STACK MENU
    // =====================================================================

    private void stackMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("STACK OPERATIONS (LIFO)");
            System.out.println("  1. Push");
            System.out.println("  2. Pop");
            System.out.println("  3. Peek (view top)");
            System.out.println("  4. Display stack");
            System.out.println("  5. LIFO demonstration (reverse a sequence)");
            System.out.println("  6. Load sample data");
            System.out.println("  7. Clear stack");
            System.out.println("  8. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 8);
            OperationStats stats = null;
            String label = null;

            switch (choice) {
                case 1 -> {
                    int value = input.readInt("Enter the value to push: ");
                    stats = stack.push(value);
                    label = "Stack Push";
                }
                case 2 -> {
                    stats = stack.pop();
                    label = "Stack Pop";
                }
                case 3 -> {
                    stats = stack.peek();
                    label = "Stack Peek";
                }
                case 4 -> {
                    ConsoleUI.printSubHeading("Current stack");
                    stats = stack.display();
                    label = "Stack Display";
                }
                case 5 -> {
                    int count = input.readIntInRange("How many numbers? (2-10): ", 2, 10);
                    int[] values = new int[count];
                    for (int i = 0; i < count; i++) {
                        values[i] = input.readInt("  Value " + (i + 1) + ": ");
                    }
                    stats = stack.demonstrateReversal(values);
                    label = "Stack LIFO Demo";
                }
                case 6 -> {
                    stack.loadSampleData();
                    ConsoleUI.printSuccess("Sample data loaded into the stack.");
                    stack.display();
                }
                case 7 -> {
                    stack.clear();
                    ConsoleUI.printSuccess("Stack cleared.");
                }
                case 8 -> back = true;
            }

            report(stats, label, "Stack");
            if (!back) {
                input.pause();
            }
        }
    }

    // =====================================================================
    //  3. QUEUE MENU
    // =====================================================================

    private void queueMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("QUEUE OPERATIONS (FIFO)");
            System.out.println("  1. Enqueue");
            System.out.println("  2. Dequeue");
            System.out.println("  3. Peek (view front)");
            System.out.println("  4. Display queue");
            System.out.println("  5. Load sample data");
            System.out.println("  6. Clear queue");
            System.out.println("  7. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 7);
            OperationStats stats = null;
            String label = null;

            switch (choice) {
                case 1 -> {
                    int value = input.readInt("Enter the value to enqueue: ");
                    stats = queue.enqueue(value);
                    label = "Queue Enqueue";
                }
                case 2 -> {
                    stats = queue.dequeue();
                    label = "Queue Dequeue";
                }
                case 3 -> {
                    stats = queue.peek();
                    label = "Queue Peek";
                }
                case 4 -> {
                    ConsoleUI.printSubHeading("Current queue");
                    stats = queue.display();
                    label = "Queue Display";
                }
                case 5 -> {
                    queue.loadSampleData();
                    ConsoleUI.printSuccess("Sample data loaded into the queue.");
                    queue.display();
                }
                case 6 -> {
                    queue.clear();
                    ConsoleUI.printSuccess("Queue cleared.");
                }
                case 7 -> back = true;
            }

            report(stats, label, "Queue");
            if (!back) {
                input.pause();
            }
        }
    }

    // =====================================================================
    //  4. LINKED LIST MENU
    // =====================================================================

    private void linkedListMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("LINKED LIST OPERATIONS");
            System.out.println("  1. Insert at beginning");
            System.out.println("  2. Insert at end");
            System.out.println("  3. Insert at position");
            System.out.println("  4. Delete by value");
            System.out.println("  5. Delete by position");
            System.out.println("  6. Search");
            System.out.println("  7. Reverse the list");
            System.out.println("  8. Display list");
            System.out.println("  9. Load sample data");
            System.out.println(" 10. Clear list");
            System.out.println(" 11. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 11);
            OperationStats stats = null;
            String label = null;

            switch (choice) {
                case 1 -> {
                    int value = input.readInt("Enter the value: ");
                    stats = linkedList.insertAtBeginning(value);
                    label = "List Insert (head)";
                }
                case 2 -> {
                    int value = input.readInt("Enter the value: ");
                    stats = linkedList.insertAtEnd(value);
                    label = "List Insert (tail)";
                }
                case 3 -> {
                    int position = input.readInt("Enter the position: ");
                    int value = input.readInt("Enter the value: ");
                    stats = linkedList.insertAtPosition(position, value);
                    label = "List Insert (position)";
                }
                case 4 -> {
                    int value = input.readInt("Enter the value to delete: ");
                    stats = linkedList.deleteByValue(value);
                    label = "List Delete (value)";
                }
                case 5 -> {
                    int position = input.readInt("Enter the position to delete: ");
                    stats = linkedList.deleteAtPosition(position);
                    label = "List Delete (position)";
                }
                case 6 -> {
                    int value = input.readInt("Enter the value to search for: ");
                    stats = linkedList.search(value);
                    label = "List Search";
                }
                case 7 -> {
                    stats = linkedList.reverse();
                    label = "List Reverse";
                    linkedList.display();
                }
                case 8 -> {
                    ConsoleUI.printSubHeading("Current linked list");
                    stats = linkedList.display();
                    label = "List Display";
                }
                case 9 -> {
                    linkedList.loadSampleData();
                    ConsoleUI.printSuccess("Sample data loaded into the linked list.");
                    linkedList.display();
                }
                case 10 -> {
                    linkedList.clear();
                    ConsoleUI.printSuccess("Linked list cleared.");
                }
                case 11 -> back = true;
            }

            report(stats, label, "LinkedList");
            if (!back) {
                input.pause();
            }
        }
    }

    // =====================================================================
    //  5. SEARCHING MENU
    // =====================================================================

    private void searchMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("SEARCHING OPERATIONS");
            System.out.println("  1. Linear search on the array");
            System.out.println("  2. Binary search on the array (sorted copy)");
            System.out.println("  3. Compare Linear vs Binary on the array");
            System.out.println("  4. Compare Linear vs Binary on a large dataset");
            System.out.println("  5. Linear search on the linked list");
            System.out.println("  6. Recursive binary search demonstration");
            System.out.println("  7. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1 -> linearSearchOnArray();
                case 2 -> binarySearchOnArray();
                case 3 -> compareSearchesOnArray();
                case 4 -> compareSearchesOnLargeDataset();
                case 5 -> linearSearchOnLinkedList();
                case 6 -> recursiveBinaryDemo();
                case 7 -> back = true;
            }

            if (!back) {
                input.pause();
            }
        }
    }

    private void linearSearchOnArray() {
        int[] data = array.toArray();
        if (data.length == 0) {
            ConsoleUI.printError("The array is empty. Load sample data from the Array menu first.");
            return;
        }
        System.out.println("  Data: " + SearchAlgorithms.format(data));
        int target = input.readInt("Enter the value to search for: ");

        OperationStats stats = searcher.linearSearch(data, target);
        report(stats, "Search (Linear)", "Linear Search");
    }

    private void binarySearchOnArray() {
        int[] data = array.toArray();
        if (data.length == 0) {
            ConsoleUI.printError("The array is empty. Load sample data from the Array menu first.");
            return;
        }
        int[] sorted = searcher.sortedCopy(data);
        System.out.println("  Original : " + SearchAlgorithms.format(data));
        System.out.println("  Sorted   : " + SearchAlgorithms.format(sorted));
        ConsoleUI.printInfo("Binary search requires sorted data, so a sorted copy was created.");

        int target = input.readInt("Enter the value to search for: ");
        OperationStats stats = searcher.binarySearch(sorted, target);
        report(stats, "Search (Binary)", "Binary Search");
    }

    private void compareSearchesOnArray() {
        int[] data = array.toArray();
        if (data.length == 0) {
            ConsoleUI.printError("The array is empty. Load sample data from the Array menu first.");
            return;
        }
        int[] sorted = searcher.sortedCopy(data);
        System.out.println("  Original : " + SearchAlgorithms.format(data));
        System.out.println("  Sorted   : " + SearchAlgorithms.format(sorted));

        int target = input.readInt("Enter the value to search for: ");

        OperationStats linear = searcher.linearSearch(data, target);
        OperationStats binary = searcher.binarySearch(sorted, target);

        ConsoleUI.printHeading("LINEAR vs BINARY  (n = " + data.length + ")");
        System.out.printf("  %-18s %10s %14s   %s%n", "Algorithm", "Steps", "Time (ms)", "Result");
        ConsoleUI.printThinLine();
        System.out.printf("  %-18s %10d %14.6f   %s%n", "Linear Search O(n)",
                linear.getSteps(), linear.getTimeMillis(), linear.getMessage());
        System.out.printf("  %-18s %10d %14.6f   %s%n", "Binary Search O(log n)",
                binary.getSteps(), binary.getTimeMillis(), binary.getMessage());
        ConsoleUI.printLine();
        explainSearchDifference(data.length, linear.getSteps(), binary.getSteps());

        tracker.record("Search", "Linear Search", linear, linear.getMessage());
        tracker.record("Search", "Binary Search", binary, binary.getMessage());
    }

    private void compareSearchesOnLargeDataset() {
        int size = input.readIntInRange("Dataset size (100 - 100000): ", 100, 100000);
        int[] sorted = SearchAlgorithms.generateSortedDataset(size);
        int worstCaseTarget = sorted[size - 1];

        ConsoleUI.printInfo("Generated a sorted dataset of " + size + " values (2, 4, 6 ... "
                + worstCaseTarget + ").");
        ConsoleUI.printInfo("Searching for the LAST value, which is the worst case for linear search.");

        OperationStats linear = searcher.linearSearch(sorted, worstCaseTarget);
        OperationStats binary = searcher.binarySearch(sorted, worstCaseTarget);

        ConsoleUI.printHeading("LINEAR vs BINARY  (n = " + size + ")");
        System.out.printf("  %-24s %12s %14s%n", "Algorithm", "Steps", "Time (ms)");
        ConsoleUI.printThinLine();
        System.out.printf("  %-24s %12d %14.6f%n", "Linear Search O(n)",
                linear.getSteps(), linear.getTimeMillis());
        System.out.printf("  %-24s %12d %14.6f%n", "Binary Search O(log n)",
                binary.getSteps(), binary.getTimeMillis());
        ConsoleUI.printLine();
        explainSearchDifference(size, linear.getSteps(), binary.getSteps());

        tracker.record("Search (n=" + size + ")", "Linear Search", linear, linear.getMessage());
        tracker.record("Search (n=" + size + ")", "Binary Search", binary, binary.getMessage());
    }

    private void explainSearchDifference(int n, long linearSteps, long binarySteps) {
        double log2n = Math.log(n) / Math.log(2);
        System.out.println("  WHY THE RESULTS DIFFER");
        System.out.println("  - Linear search examines elements one by one, so in the worst case");
        System.out.println("    it performs n = " + n + " comparisons. This is O(n).");
        System.out.printf("  - Binary search halves the remaining range each time, so it needs at%n");
        System.out.printf("    most log2(%d) = %.1f comparisons. This is O(log n).%n", n, log2n);
        if (binarySteps > 0) {
            System.out.printf("  - Measured ratio: %d steps vs %d steps = binary search was about%n",
                    linearSteps, binarySteps);
            System.out.printf("    %.1f times cheaper in comparisons for this input.%n",
                    (double) linearSteps / binarySteps);
        }
        System.out.println("  - TRADE-OFF: binary search only works on SORTED data. If the data must");
        System.out.println("    first be sorted (O(n log n) or O(n^2)), a single linear search on");
        System.out.println("    unsorted data can be the better choice.");
        ConsoleUI.printLine();
    }

    private void linearSearchOnLinkedList() {
        if (linkedList.isEmpty()) {
            ConsoleUI.printError("The linked list is empty. Load sample data first.");
            return;
        }
        linkedList.display();
        int target = input.readInt("Enter the value to search for: ");
        OperationStats stats = linkedList.search(target);
        report(stats, "Search (List)", "Linked List Search");
        ConsoleUI.printInfo("A linked list cannot use binary search because it has no direct");
        ConsoleUI.printInfo("index access - reaching the middle node itself costs O(n).");
    }

    private void recursiveBinaryDemo() {
        int[] data = array.toArray();
        if (data.length == 0) {
            ConsoleUI.printError("The array is empty. Load sample data from the Array menu first.");
            return;
        }
        int[] sorted = searcher.sortedCopy(data);
        System.out.println("  Sorted data: " + SearchAlgorithms.format(sorted));
        int target = input.readInt("Enter the value to search for: ");

        OperationStats iterative = searcher.binarySearch(sorted, target);
        OperationStats recursive = searcher.binarySearchRecursive(sorted, target);

        System.out.println();
        System.out.printf("  Iterative binary search : %d step(s) - %s%n",
                iterative.getSteps(), iterative.getMessage());
        System.out.printf("  Recursive binary search : %d step(s) - %s%n",
                recursive.getSteps(), recursive.getMessage());
        ConsoleUI.printInfo("Both forms are O(log n). The recursive version uses O(log n) call-stack");
        ConsoleUI.printInfo("space, while the iterative version uses O(1) extra space.");

        tracker.record("Search", "Binary (iterative)", iterative, iterative.getMessage());
        tracker.record("Search", "Binary (recursive)", recursive, recursive.getMessage());
    }

    // =====================================================================
    //  6. GRAPH MENU
    // =====================================================================

    private void graphMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("GRAPH OPERATIONS");
            System.out.println("  1. Add vertex");
            System.out.println("  2. Add edge");
            System.out.println("  3. Remove vertex");
            System.out.println("  4. Remove edge");
            System.out.println("  5. Display graph (adjacency list)");
            System.out.println("  6. Display graph (adjacency matrix)");
            System.out.println("  7. BFS traversal");
            System.out.println("  8. DFS traversal");
            System.out.println("  9. Recursive DFS traversal");
            System.out.println(" 10. Search for a path between two vertices");
            System.out.println(" 11. Compare BFS vs DFS");
            System.out.println(" 12. Check connectivity");
            System.out.println(" 13. Load sample graph (Sri Lankan cities)");
            System.out.println(" 14. Clear graph");
            System.out.println(" 15. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 15);
            OperationStats stats = null;
            String label = null;

            switch (choice) {
                case 1 -> {
                    String vertex = input.readNonEmptyString("Enter the vertex name: ");
                    stats = graph.addVertex(vertex);
                    label = "Graph Add Vertex";
                }
                case 2 -> {
                    String source = input.readNonEmptyString("Enter the source vertex: ");
                    String destination = input.readNonEmptyString("Enter the destination vertex: ");
                    stats = graph.addEdge(source, destination);
                    label = "Graph Add Edge";
                }
                case 3 -> {
                    String vertex = input.readNonEmptyString("Enter the vertex to remove: ");
                    stats = graph.removeVertex(vertex);
                    label = "Graph Remove Vertex";
                }
                case 4 -> {
                    String source = input.readNonEmptyString("Enter the source vertex: ");
                    String destination = input.readNonEmptyString("Enter the destination vertex: ");
                    stats = graph.removeEdge(source, destination);
                    label = "Graph Remove Edge";
                }
                case 5 -> {
                    ConsoleUI.printSubHeading("Graph - adjacency list");
                    stats = graph.displayAdjacencyList();
                    label = "Graph Display (list)";
                }
                case 6 -> {
                    ConsoleUI.printSubHeading("Graph - adjacency matrix");
                    stats = graph.displayAdjacencyMatrix();
                    label = "Graph Display (matrix)";
                }
                case 7 -> {
                    String startVertex = askStartVertex();
                    if (startVertex != null) {
                        ConsoleUI.printSubHeading("BFS traversal");
                        stats = graph.bfs(startVertex);
                        label = "Graph Traversal";
                        tracker.record("Graph Traversal", "BFS", stats, stats.getMessage());
                        ConsoleUI.printStats(stats);
                        stats = null;   // already recorded
                    }
                }
                case 8 -> {
                    String startVertex = askStartVertex();
                    if (startVertex != null) {
                        ConsoleUI.printSubHeading("DFS traversal");
                        stats = graph.dfs(startVertex);
                        label = "Graph Traversal";
                        tracker.record("Graph Traversal", "DFS", stats, stats.getMessage());
                        ConsoleUI.printStats(stats);
                        stats = null;
                    }
                }
                case 9 -> {
                    String startVertex = askStartVertex();
                    if (startVertex != null) {
                        ConsoleUI.printSubHeading("Recursive DFS traversal");
                        stats = graph.dfsRecursive(startVertex);
                        label = "Graph Traversal";
                        tracker.record("Graph Traversal", "DFS (recursive)", stats, stats.getMessage());
                        ConsoleUI.printStats(stats);
                        stats = null;
                    }
                }
                case 10 -> {
                    if (graph.isEmpty()) {
                        ConsoleUI.printError("The graph is empty. Load the sample graph first.");
                    } else {
                        graph.displayAdjacencyList();
                        String source = input.readNonEmptyString("Enter the source vertex: ");
                        String destination = input.readNonEmptyString("Enter the destination vertex: ");
                        stats = graph.findPath(source, destination);
                        label = "Graph Path Search";
                    }
                }
                case 11 -> compareTraversals();
                case 12 -> {
                    stats = graph.checkConnectivity();
                    label = "Graph Connectivity";
                }
                case 13 -> {
                    graph.loadSampleData();
                    ConsoleUI.printSuccess("Sample graph of Sri Lankan cities loaded.");
                    graph.displayAdjacencyList();
                }
                case 14 -> {
                    graph.clear();
                    ConsoleUI.printSuccess("Graph cleared.");
                }
                case 15 -> back = true;
            }

            report(stats, label, "Graph");
            if (!back) {
                input.pause();
            }
        }
    }

    private String askStartVertex() {
        if (graph.isEmpty()) {
            ConsoleUI.printError("The graph is empty. Load the sample graph (option 13) first.");
            return null;
        }
        System.out.println("  Available vertices: " + String.join(", ", graph.getVertices()));
        String vertex = input.readNonEmptyString("Enter the starting vertex: ");
        if (!graph.hasVertex(vertex)) {
            ConsoleUI.printError("Vertex '" + vertex + "' does not exist in the graph.");
            return null;
        }
        return vertex;
    }

    private void compareTraversals() {
        String startVertex = askStartVertex();
        if (startVertex == null) {
            return;
        }

        ConsoleUI.printSubHeading("BFS");
        OperationStats bfs = graph.bfs(startVertex);
        ConsoleUI.printSubHeading("DFS");
        OperationStats dfs = graph.dfs(startVertex);

        ConsoleUI.printHeading("BFS vs DFS  (V = " + graph.getVertexCount()
                + ", E = " + graph.getEdgeCount() + ")");
        System.out.printf("  %-22s %10s %14s%n", "Traversal", "Steps", "Time (ms)");
        ConsoleUI.printThinLine();
        System.out.printf("  %-22s %10d %14.6f%n", "BFS (queue, O(V+E))",
                bfs.getSteps(), bfs.getTimeMillis());
        System.out.printf("  %-22s %10d %14.6f%n", "DFS (stack, O(V+E))",
                dfs.getSteps(), dfs.getTimeMillis());
        ConsoleUI.printLine();
        System.out.println("  WHY THE RESULTS DIFFER");
        System.out.println("  - Both traversals are O(V + E): every vertex is visited once and");
        System.out.println("    every edge is examined once, so the totals are of the same order.");
        System.out.println("  - The VISIT ORDER differs. BFS uses a queue and expands level by");
        System.out.println("    level, so it finds the fewest-hop path first. DFS uses a stack and");
        System.out.println("    follows one branch to its end before backtracking.");
        System.out.println("  - Step counts can differ slightly because DFS may push a vertex more");
        System.out.println("    than once and discard duplicates when popping.");
        System.out.println("  - MEMORY: BFS holds a whole level in the queue (O(V) worst case);");
        System.out.println("    DFS holds only the current path (O(depth)).");
        ConsoleUI.printLine();

        tracker.record("Graph Traversal", "BFS", bfs, bfs.getMessage());
        tracker.record("Graph Traversal", "DFS", dfs, dfs.getMessage());
    }

    // =====================================================================
    //  7. PERFORMANCE MENU
    // =====================================================================

    private void performanceMenu() {
        boolean back = false;
        while (!back) {
            ConsoleUI.printHeading("PERFORMANCE COMPARISON");
            System.out.println("  1. Show all recorded operations");
            System.out.println("  2. Show search comparison only");
            System.out.println("  3. Run the automatic benchmark (all components)");
            System.out.println("  4. Show complexity reference table");
            System.out.println("  5. Clear recorded results");
            System.out.println("  6. Return to Main Menu");
            ConsoleUI.printLine();

            int choice = input.readIntInRange("Enter your choice: ", 1, 6);
            switch (choice) {
                case 1 -> tracker.displayAll();
                case 2 -> tracker.displaySearchComparison();
                case 3 -> runAutomaticBenchmark();
                case 4 -> printComplexityTable();
                case 5 -> {
                    tracker.clear();
                    ConsoleUI.printSuccess("All recorded results cleared.");
                }
                case 6 -> back = true;
            }
            if (!back) {
                input.pause();
            }
        }
    }

    /**
     * Runs one representative operation on every component and fills the
     * performance table in a single click. This is the fastest way to show
     * the complete Performance Comparison requirement during the video.
     */
    private void runAutomaticBenchmark() {
        ConsoleUI.printHeading("AUTOMATIC BENCHMARK");
        ConsoleUI.printInfo("Running one representative operation on every component...");

        // Make sure there is data to work with.
        array.loadSampleData();
        stack.loadSampleData();
        queue.loadSampleData();
        linkedList.loadSampleData();
        if (graph.isEmpty()) {
            graph.loadSampleData();
        }

        int[] data = array.toArray();
        int[] sorted = searcher.sortedCopy(data);
        int target = sorted[sorted.length - 1];   // worst case for linear search

        OperationStats arrayInsert = array.insertAtEnd(99);
        tracker.record("Insert", "Array (at end)", arrayInsert, arrayInsert.getMessage());

        OperationStats listInsert = linkedList.insertAtEnd(99);
        tracker.record("Insert", "Linked List (at end)", listInsert, listInsert.getMessage());

        OperationStats push = stack.push(99);
        tracker.record("Insert", "Stack (push)", push, push.getMessage());

        OperationStats enqueue = queue.enqueue(99);
        tracker.record("Insert", "Queue (enqueue)", enqueue, enqueue.getMessage());

        OperationStats linear = searcher.linearSearch(sorted, target);
        tracker.record("Search", "Linear Search", linear, linear.getMessage());

        OperationStats binary = searcher.binarySearch(sorted, target);
        tracker.record("Search", "Binary Search", binary, binary.getMessage());

        OperationStats listSearch = linkedList.search(target);
        tracker.record("Search", "Linked List Search", listSearch, listSearch.getMessage());

        String startVertex = graph.getVertices().iterator().next();
        OperationStats bfs = graph.bfs(startVertex);
        tracker.record("Graph Traversal", "BFS", bfs, bfs.getMessage());

        OperationStats dfs = graph.dfs(startVertex);
        tracker.record("Graph Traversal", "DFS", dfs, dfs.getMessage());

        ConsoleUI.printSuccess("Benchmark complete. 9 operations recorded.");
        tracker.displayAll();
    }

    private void printComplexityTable() {
        ConsoleUI.printHeading("COMPLEXITY REFERENCE");
        System.out.printf("  %-24s %-14s %-14s %-12s%n",
                "Structure / Algorithm", "Access", "Search", "Insert/Delete");
        ConsoleUI.printThinLine();
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Array", "O(1)", "O(n)", "O(n)");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Stack (array)", "O(1) top", "O(n)", "O(1)");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Queue (circular)", "O(1) front", "O(n)", "O(1)");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Singly Linked List", "O(n)", "O(n)", "O(1) at head");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Linear Search", "-", "O(n)", "-");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Binary Search", "-", "O(log n)", "-");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Insertion Sort", "-", "-", "O(n^2)");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Graph BFS", "-", "O(V+E)", "-");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Graph DFS", "-", "O(V+E)", "-");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Adjacency List", "O(degree)", "O(V+E)", "O(1) add edge");
        System.out.printf("  %-24s %-14s %-14s %-12s%n", "Adjacency Matrix", "O(1)", "O(V^2)", "O(1) add edge");
        ConsoleUI.printLine();
        System.out.println("  Space: Array/Stack/Queue O(n) | Linked List O(n) + pointer overhead");
        System.out.println("         Adjacency List O(V+E) | Adjacency Matrix O(V^2)");
        ConsoleUI.printLine();
    }

    // =====================================================================
    //  8. DISPLAY ALL RESULTS
    // =====================================================================

    private void displayAllResults() {
        ConsoleUI.printHeading("DISPLAY ALL RESULTS");

        ConsoleUI.printSubHeading("ARRAY");
        array.display();

        ConsoleUI.printSubHeading("STACK");
        stack.display();

        ConsoleUI.printSubHeading("QUEUE");
        queue.display();

        ConsoleUI.printSubHeading("LINKED LIST");
        linkedList.display();

        ConsoleUI.printSubHeading("GRAPH");
        graph.displayAdjacencyList();

        tracker.displayAll();
        input.pause();
    }

    // =====================================================================
    //  SHARED HELPER
    // =====================================================================

    /**
     * Prints the outcome of an operation and records it in the performance
     * tracker. Keeping this in one place guarantees that every component
     * reports results in the same format.
     */
    private void report(OperationStats stats, String operationLabel, String structureLabel) {
        if (stats == null || operationLabel == null) {
            return;
        }
        if (stats.isSuccessful()) {
            ConsoleUI.printSuccess(stats.getMessage());
        } else {
            ConsoleUI.printError(stats.getMessage());
        }
        ConsoleUI.printStats(stats);
        tracker.record(operationLabel, structureLabel, stats, stats.getMessage());
    }
}
