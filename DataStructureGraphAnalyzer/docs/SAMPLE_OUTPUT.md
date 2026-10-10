# Sample Console Output

Real output captured from a test run. Use this to check your build behaves the
same way, and as a reference while recording the video.

## Startup

```
============================================================
      CIT300 - DATA STRUCTURES AND ALGORITHMS
      GRADED PRACTICAL ASSIGNMENT 2
      DATA STRUCTURE & GRAPH PERFORMANCE ANALYZER
      Sri Lanka Technology Campus (SLTC)
============================================================

Load sample demonstration data into all structures? (y/n): y
[OK]    Sample data loaded into Array, Stack, Queue, Linked List and Graph.

============================================================
             DATA STRUCTURE & GRAPH ANALYZER
============================================================
  1. Array Operations
  2. Stack Operations
  3. Queue Operations
  4. Linked List Operations
  5. Searching Operations
  6. Graph Operations
  7. Performance Comparison
  8. Display All Results
  9. Exit
============================================================
Enter your choice:
```

## Array display

```
--- Current array ------------------------------------------
        Index :      0     1     2     3     4     5     6     7     8     9
        Value :     45    12    78    33    91     5    60    27    84    19
        Size = 10 / Capacity = 20
[OK]    Displayed 10 element(s).
        Steps: 10  |  Time: 0.041000 ms
```

## Stack display

```
        (top of stack)
        | 50       |  <-- TOP
        | 40       |
        | 30       |
        | 20       |
        | 10       |
        +----------+
        (bottom of stack)   size = 5 / 20
```

## Queue display

```
        FRONT -> [101] [102] [103] [104] [105] <- REAR
        size = 5 / 20   (front index = 0, rear index = 4)
```

## Linked list display

```
        HEAD -> [7] -> [14] -> [21] -> [28] -> [35] -> [42] -> NULL
        Number of nodes = 6
```

## Error handling (empty structures)

```
[ERROR] STACK UNDERFLOW - cannot pop from an empty stack.
[ERROR] Stack is EMPTY - there is no top element to peek.
[ERROR] QUEUE UNDERFLOW - cannot dequeue from an empty queue.
[ERROR] Array is EMPTY. Nothing to delete.
[ERROR] The graph is empty. Load the sample graph (option 13) first.
[ERROR] Input cannot be empty. Please enter a whole number.
[ERROR] 'abc' is not a valid integer. Please try again.
[ERROR] Please enter a number between 1 and 9.
```

## Graph — adjacency list

```
        Representation : ADJACENCY LIST
        Graph type     : UNDIRECTED

        Colombo      -> Kandy  Galle  Negombo
        Kandy        -> Colombo  Badulla  Ampara
        Galle        -> Colombo  Matara
        Negombo      -> Colombo  Kurunegala
        Badulla      -> Kandy  ArugamBay
        Ampara       -> Kandy  ArugamBay
        Matara       -> Galle
        ArugamBay    -> Badulla  Ampara
        Kurunegala   -> Negombo

        Vertices = 9 , Edges = 9
```

## Graph — BFS vs DFS comparison

```
--- BFS ----------------------------------------------------
        BFS uses a QUEUE (FIFO) - nearest vertices first.
        Visit order : Colombo -> Kandy -> Galle -> Negombo -> Badulla -> Ampara -> Matara -> Kurunegala -> ArugamBay
        Vertices visited : 9 of 9

--- DFS ----------------------------------------------------
        DFS uses a STACK (LIFO) - one branch fully, then backtrack.
        Visit order : Colombo -> Kandy -> Badulla -> ArugamBay -> Ampara -> Galle -> Matara -> Negombo -> Kurunegala
        Vertices visited : 9 of 9

============================================================
                 BFS vs DFS  (V = 9, E = 9)
============================================================
  Traversal                   Steps      Time (ms)
------------------------------------------------------------
  BFS (queue, O(V+E))            28       1.731812
  DFS (stack, O(V+E))            29       0.643665
============================================================
  WHY THE RESULTS DIFFER
  - Both traversals are O(V + E): every vertex is visited once and
    every edge is examined once, so the totals are of the same order.
  - The VISIT ORDER differs. BFS uses a queue and expands level by
    level, so it finds the fewest-hop path first. DFS uses a stack and
    follows one branch to its end before backtracking.
  - MEMORY: BFS holds a whole level in the queue (O(V) worst case);
    DFS holds only the current path (O(depth)).
============================================================
```

## Searching — Linear vs Binary on 10,000 elements

```
               LINEAR vs BINARY  (n = 10000)
============================================================
  Algorithm                       Steps      Time (ms)
------------------------------------------------------------
  Linear Search O(n)              10000       0.325239
  Binary Search O(log n)             14       0.001735
============================================================
  WHY THE RESULTS DIFFER
  - Linear search examines elements one by one, so in the worst case
    it performs n = 10000 comparisons. This is O(n).
  - Binary search halves the remaining range each time, so it needs at
    most log2(10000) = 13.3 comparisons. This is O(log n).
  - Measured ratio: 10000 steps vs 14 steps = binary search was about
    714.3 times cheaper in comparisons for this input.
  - TRADE-OFF: binary search only works on SORTED data. If the data must
    first be sorted (O(n log n) or O(n^2)), a single linear search on
    unsorted data can be the better choice.
============================================================
```

## Performance table after the automatic benchmark

```
============================================================
                   PERFORMANCE COMPARISON
============================================================
Operation              Algorithm / Structure       Steps      Time (ms)   Result
-------------------------------------------------------------------------------
Search (n=10000)       Linear Search               10000       0.325239   FOUND at index 9999 ...
Search (n=10000)       Binary Search                  14       0.001735   FOUND at index 9999 ...
Graph Traversal        BFS                            28       1.731812   BFS from 'Colombo' visited 9 vertices.
Graph Traversal        DFS                            29       0.643665   DFS from 'Colombo' visited 9 vertices.
Insert                 Array (at end)                  2       0.001213   Inserted 99 at index 10.
Insert                 Linked List (at end)            7       0.002470   Inserted 99 at the end. Size = 7.
Insert                 Stack (push)                    2       0.002625   Pushed 99. Stack size is now 6.
Insert                 Queue (enqueue)                 2       0.002727   Enqueued 99. Queue size is now 6.
Search                 Linked List Search              7       0.004438   Value 91 NOT FOUND ...
===============================================================================
Total recorded operations : 9

NOTE: 'Steps' counts elementary operations (comparisons / visits) and is
      hardware independent, so it is the reliable column for comparing
      algorithmic complexity. 'Time' varies with CPU load and JIT warm-up.
```

> Timing numbers will differ on your machine. The **step counts will be
> identical**, which is exactly the point made in `COMPLEXITY_NOTES.md`.
