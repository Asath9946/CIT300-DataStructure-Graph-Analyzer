# Complexity Notes — what to say in the video and the viva

Section 4 of the brief says: *"Students must be able to explain why the results
may differ and relate their explanation to algorithmic complexity."*
These are the answers. Read them until you can say them in your own words — the
lecturer will ask follow-up questions.

---

## 1. Array

| Operation | Complexity | Why |
|-----------|------------|-----|
| Access by index | O(1) | The address is calculated directly: `base + index * size`. No searching. |
| Insert at end | O(1) | One write into the next free slot. |
| Insert at index | O(n) | Every element after the index must shift one place right. |
| Delete at index | O(n) | Every element after the index must shift one place left. |
| Linear search | O(n) | Worst case every element is compared. |

**Key line:** an array is fast to *read* and slow to *change in the middle*.

---

## 2. Stack (LIFO)

All of push, pop and peek are **O(1)** because every one of them touches only
the `top` pointer and one array cell. Nothing shifts.

**Why underflow matters:** popping an empty stack is not a logic error we can
ignore. In this project `pop()` returns a failed `OperationStats` with the
message `STACK UNDERFLOW`, so the program keeps running instead of throwing.

**Real use:** undo history, browser back button, function call stack,
expression evaluation, and the explicit stack inside our iterative DFS.

---

## 3. Queue (FIFO)

Enqueue, dequeue and peek are all **O(1)**.

**Why we used a CIRCULAR queue, not a linear one.** In a linear array queue the
`rear` pointer eventually reaches the end of the array. Even if elements have
been dequeued from the front, there is no space left at the rear, so the queue
reports "full" while half the array is empty. The fix would be to shift every
element forward, which costs O(n) per dequeue. The circular queue instead wraps
the index with `(rear + 1) % capacity`, so dequeue stays O(1) and no space is
wasted.

**Real use:** print queues, CPU scheduling, request buffers, and the queue
inside our BFS traversal.

---

## 4. Linked List

| Operation | Complexity | Why |
|-----------|------------|-----|
| Insert at beginning | O(1) | Point the new node at the old head, move head. |
| Insert at end | O(n) | We must walk from head to tail. (O(1) if a tail pointer is kept.) |
| Delete by value | O(n) | We must find the node first. |
| Search | O(n) | No index arithmetic; we follow `next` references. |
| Reverse | O(n) | One pass with three pointers. |

### Array vs Linked List — the comparison question

| | Array | Linked List |
|---|-------|-------------|
| Memory | One contiguous block, fixed size | Scattered nodes, grows on demand |
| Access element #k | O(1) | O(n) |
| Insert at front | O(n) — everything shifts | O(1) |
| Extra memory | None | One reference per node |
| Binary search possible? | Yes | No — reaching the middle already costs O(n) |

**Key line:** choose the array when you read a lot by index; choose the linked
list when you insert and delete a lot, especially at the front.

---

## 5. Searching — the most important comparison in this assignment

| Algorithm | Best | Average | Worst | Requires sorted data? |
|-----------|------|---------|-------|------------------------|
| Linear search | O(1) | O(n) | O(n) | No |
| Binary search | O(1) | O(log n) | O(log n) | **Yes** |

### Why the step counts differ

Linear search checks elements one at a time, so to find the last element of an
array of 10,000 it performs 10,000 comparisons.

Binary search throws away **half of the remaining data on every comparison**.
After 1 comparison 5,000 remain, after 2 comparisons 2,500, after 3 comparisons
1,250 and so on. The number of comparisons needed is therefore the number of
times you can halve n before reaching 1, which is log₂(n). For n = 10,000 that
is about 13 to 14 comparisons.

**Measured result from our program:**

```
n = 10000, searching for the last element (worst case for linear)
Linear Search O(n)        10000 steps
Binary Search O(log n)       14 steps      ~714x fewer comparisons
```

### The trade-off you must mention (this is the mark-scoring point)

Binary search is only faster **if the data is already sorted**. Sorting the data
first costs O(n log n) with a good sort, or O(n²) with the insertion sort used
in this project. So:

- Searching an unsorted array **once** → linear search wins, because sorting
  costs more than the single scan.
- Searching the same dataset **many times** → sort once, then every search is
  O(log n) and binary search wins overall.

### Why time in milliseconds is less reliable than step count

Step count is deterministic and hardware independent. Execution time varies with
CPU load, other processes, JVM JIT warm-up and garbage collection. In small
tests the *first* measured operation often looks slower than later ones purely
because the JVM has not yet optimised that code path. That is why the program
reports both columns and the comparison discussion is based on **steps**.

---

## 6. Graph

### Representation

| | Adjacency List | Adjacency Matrix |
|---|----------------|------------------|
| Space | O(V + E) | O(V²) |
| Check if edge (u,v) exists | O(degree of u) | O(1) |
| Find all neighbours of u | O(degree of u) | O(V) |
| Best for | Sparse graphs | Dense graphs |

We chose the **adjacency list** because real networks (roads, social graphs,
routes) are sparse: most pairs of vertices are not connected, so a matrix would
waste most of its cells storing zeros. Our sample graph has 9 vertices and 9
edges: the matrix needs 81 cells, the list needs 27 references.

### BFS vs DFS

| | BFS | DFS |
|---|-----|-----|
| Helper structure | Queue (FIFO) | Stack (LIFO), or recursion |
| Exploration order | Level by level, nearest first | One branch to the end, then backtrack |
| Time complexity | O(V + E) | O(V + E) |
| Space complexity | O(V) — a whole level can sit in the queue | O(depth of the graph) |
| Finds shortest path in an unweighted graph? | **Yes** | No |
| Typical use | Shortest route, social network "degrees of separation", peer-to-peer search | Cycle detection, topological sort, maze solving, connected components |

**Both are O(V + E)** because each vertex is visited once and each edge is
examined once. That is why their step counts in our table are very close (28 vs
29 on the sample graph). The difference is not speed — it is **order of visit
and memory profile**.

The small step difference happens because our iterative DFS may push the same
vertex onto the stack more than once (it is discarded when popped if already
visited), whereas BFS marks a vertex as visited at the moment it is enqueued.

**Sample output from our program (start = Colombo):**

```
BFS : Colombo -> Kandy -> Galle -> Negombo -> Badulla -> Ampara -> Matara -> Kurunegala -> ArugamBay
DFS : Colombo -> Kandy -> Badulla -> ArugamBay -> Ampara -> Galle -> Matara -> Negombo -> Kurunegala
```

Notice BFS reaches all of Colombo's direct neighbours (Kandy, Galle, Negombo)
before going any deeper, while DFS dives Colombo → Kandy → Badulla → ArugamBay
immediately.

---

## 7. Likely viva questions and short answers

**Q: Why is binary search not used on the linked list?**
A: Binary search needs O(1) access to the middle element. In a linked list,
reaching the middle requires walking n/2 nodes, so the advantage disappears.

**Q: Why did you use a circular queue?**
A: To keep dequeue at O(1) without wasting the array space in front of the
`front` pointer.

**Q: What is the space complexity of your BFS?**
A: O(V) — the visited list plus the queue, which in the worst case holds an
entire level of the graph.

**Q: Your DFS step count is higher than BFS. Is DFS slower?**
A: No. Both are O(V + E). The difference comes from our implementation pushing
duplicates onto the stack, which is a constant-factor effect, not a change in
complexity.

**Q: Why count steps instead of just measuring time?**
A: Time depends on the machine, the JVM and background load. Step count is the
direct measurement of the algorithm itself and is what Big-O describes.

**Q: What would you improve?**
A: Add a tail pointer to the linked list to make insert-at-end O(1); use a
`HashSet` for the visited collection in BFS/DFS so the `contains` check becomes
O(1) instead of O(V); support weighted edges and Dijkstra's algorithm.
