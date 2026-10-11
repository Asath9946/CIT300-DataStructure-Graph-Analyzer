# Data Structure & Graph Performance Analyzer

**CIT300 – Data Structures and Algorithms**
Graded Practical Assignment 2 (Week 12)
Sri Lanka Technology Campus (SLTC Research University)

---

## 1. Project Description

A single integrated **Java console application** that demonstrates the practical
application of core data structures, searching algorithms, graph representation
and traversal, and algorithmic complexity analysis.

The user can create and manipulate an Array, Stack, Queue, Linked List and
Graph from one menu-driven system. Every operation is instrumented: the program
counts the number of elementary steps performed and measures execution time, so
that the **Performance Comparison** module can show with real numbers why
O(n) and O(log n) behave differently, and why BFS and DFS visit vertices in a
different order even though both are O(V + E).

---

## 2. Team Members

> **FILL THIS IN BEFORE SUBMITTING.** Section 5 and 7 of the assignment brief
> require the full name, student ID, assigned responsibility and individual
> contribution of every member. Missing or incorrect details lose marks.

| # | Full Name | Student ID | Assigned Responsibility |
|---|-----------|------------|--------------------------|
| 1 | _______________________ | ____________ | Array + Searching |
| 2 | _______________________ | ____________ | Stack + Queue |
| 3 | _______________________ | ____________ | Linked List |
| 4 | _______________________ | ____________ | Graph + BFS/DFS |
| 5 | _______________________ | ____________ | Performance comparison, main menu, integration, testing |

*(If the group has fewer than five members, combine responsibilities and record
exactly who took over which component. No component may be dropped.)*

### Individual Contributions

**Student Name:** ____________________
**Student ID:** ____________________
**Assigned Responsibility:** Array and Searching
**Individual Contribution:**
- Implemented the `ArrayStructure` class (insert at end / at index, delete by
  index / by value, update, linear search, display)
- Implemented the `SearchAlgorithms` class (linear search, iterative and
  recursive binary search, insertion sort helper)
- Implemented the step counting used in the search comparison
- Tested array boundary conditions (full array, empty array, invalid index)
- Integrated the Array and Searching menus with the main application

**Student Name:** M. I. M. Naizar
**Student ID:** 23DA2-1052
**Assigned Responsibility:** Stack and Queue
**Individual Contribution:**
- Implemented the StackStructure class with push, pop, peek and display
- Added stack overflow and stack underflow handling
- Implemented the QueueStructure class as a circular array queue
- Added queue overflow and queue underflow handling
- Implemented the LIFO reversal demonstration
- Tested both structures with empty and full conditions

**Student Name:** ____________________
**Student ID:** ____________________
**Assigned Responsibility:** Linked List
**Individual Contribution:**
- Implemented the `LinkedListStructure` class with an inner `Node` class
- Implemented insert at beginning / end / position, delete by value and by
  position, search, in-place reversal and display
- Compared linked list insertion cost against array insertion cost
- Integrated the Linked List menu with the main application

**Student Name:** ____________________
**Student ID:** ____________________
**Assigned Responsibility:** Graph Component
**Individual Contribution:**
- Implemented the `Graph` class using an adjacency list representation
- Implemented add vertex, add edge, remove vertex, remove edge
- Implemented adjacency list and adjacency matrix display
- Implemented BFS traversal (queue based) and DFS traversal (stack based and
  recursive)
- Implemented shortest-path search and the connectivity check
- Tested graph functionality and integrated the graph module with the main
  application

**Student Name:** ____________________
**Student ID:** ____________________
**Assigned Responsibility:** Performance Comparison, Main Menu, Integration and Testing
**Individual Contribution:**
- Implemented `Main` as the integration layer that owns one shared instance of
  every structure
- Implemented `PerformanceTracker`, `OperationStats` and the performance
  comparison tables
- Implemented `InputValidator` so no invalid input can crash the program
- Implemented `ConsoleUI` for consistent formatting across all menus
- Implemented the automatic benchmark and the complexity reference table
- Carried out full system testing

---

## 3. Technologies Used

| Item | Detail |
|------|--------|
| Language | Java (SE 17 or later; tested on OpenJDK 21) |
| Application type | Console-based system |
| Paradigm | Object-oriented – encapsulation, separation of concerns, one class per data structure |
| External libraries | None. All data structures are implemented from first principles. |
| Version control | Git + GitHub (branches, commits, pull requests) |
| IDE | IntelliJ IDEA / Eclipse / VS Code (any) |

---

## 4. Main System Features

### A. Array
Insert at end, insert at index, delete by index, delete by value, update,
linear search, display with index labels, capacity and overflow handling.

### B. Stack (LIFO)
Push, pop, peek, display top-to-bottom, LIFO reversal demonstration.
Stack overflow and **stack underflow (pop/peek on an empty stack)** are both
handled with clear messages instead of exceptions.

### C. Queue (FIFO)
Circular array implementation. Enqueue, dequeue, peek, display front-to-rear.
Queue overflow and **queue underflow (dequeue on an empty queue)** are handled.

### D. Linked List
Singly linked list with an inner `Node` class. Insert at beginning / end /
position, delete by value / position, search, in-place reversal, display.

### E. Searching
- Linear search – works on unsorted data, O(n)
- Binary search – iterative and recursive, requires sorted data, O(log n)
- Insertion sort helper used to prepare data for binary search
- Side-by-side comparison on the live array and on a generated dataset of up
  to 100,000 elements

### F. Graph (mandatory component)
- Adjacency list representation, with an adjacency matrix view for comparison
- Add vertex, add edge, remove vertex, remove edge
- **BFS traversal** (queue based)
- **DFS traversal** (stack based) and a recursive DFS variant
- Shortest-path search between two vertices (fewest hops)
- Connectivity check
- Sample dataset of Sri Lankan cities so the demonstration uses meaningful data

### G. Performance / Complexity Demonstration
- Every operation records **step count** and **execution time in nanoseconds**
- Full performance table of all recorded operations
- Dedicated Linear vs Binary and BFS vs DFS comparisons with a written
  explanation of why the results differ
- One-click automatic benchmark that fills the table across all components
- Complexity reference table for every structure and algorithm

### H. Integration
All six components share one `Main` class and one `PerformanceTracker`
instance, so results recorded in one module are visible in every other module.
Option 8, *Display All Results*, prints the live state of every structure plus
the complete performance table.

---

## 5. Project Structure

```
DataStructureGraphAnalyzer/
├── README.md
├── .gitignore
├── run.sh                       # compile + run on Linux / macOS
├── run.bat                      # compile + run on Windows
├── docs/
│   ├── COMPLEXITY_NOTES.md      # the complexity answers for the viva
│   ├── VIDEO_SCRIPT.md          # demonstration video script
│   ├── GITHUB_WORKFLOW.md       # branch / commit / PR instructions
│   └── SAMPLE_OUTPUT.md         # expected console output
└── src/
    └── com/sltc/dsa/
        ├── Main.java                        # integration + all menus
        ├── structures/
        │   ├── ArrayStructure.java          # Component A
        │   ├── StackStructure.java          # Component B
        │   ├── QueueStructure.java          # Component C
        │   ├── LinkedListStructure.java     # Component D
        │   └── Graph.java                   # Component F
        ├── algorithms/
        │   └── SearchAlgorithms.java        # Component E
        └── util/
            ├── OperationStats.java          # steps + time of one operation
            ├── PerformanceTracker.java      # the performance table
            ├── InputValidator.java          # all input validation
            └── ConsoleUI.java               # console formatting
```

---

## 6. Instructions for Running the Program

### Requirement
Java JDK 17 or later. Check with:

```bash
java -version
javac -version
```

### Option 1 – one command (recommended)

Linux / macOS:
```bash
chmod +x run.sh
./run.sh
```

Windows:
```cmd
run.bat
```

### Option 2 – manual compile and run

Linux / macOS:
```bash
mkdir -p build
javac -d build $(find src -name "*.java")
java -cp build com.sltc.dsa.Main
```

Windows (Command Prompt):
```cmd
mkdir build
dir /s /B src\*.java > sources.txt
javac -d build @sources.txt
java -cp build com.sltc.dsa.Main
```

### Option 3 – from an IDE
Open the project folder, mark `src` as the sources root, and run
`com.sltc.dsa.Main`.

### First steps inside the program
1. Answer `y` to *Load sample demonstration data* so every structure has data.
2. Try option **6 → 11** to compare BFS and DFS.
3. Try option **5 → 4** with a dataset of 10000 to see O(n) vs O(log n).
4. Try option **7 → 3** to run the automatic benchmark.
5. Try option **8** to display the full system state and the performance table.

---

## 7. Example Main Menu

```
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

---

## 8. Sample Performance Output

```
Operation              Algorithm / Structure       Steps      Time (ms)
-------------------------------------------------------------------------
Search (n=10000)       Linear Search               10000       0.325239
Search (n=10000)       Binary Search                  14       0.001735
Graph Traversal        BFS                            28       1.731812
Graph Traversal        DFS                            29       0.643665
```

Explanation of these results is in `docs/COMPLEXITY_NOTES.md`.

---

## 9. Input Validation and Error Handling

| Condition | Behaviour |
|-----------|-----------|
| Non-numeric menu input | Re-prompts; never throws `InputMismatchException` |
| Menu number out of range | Re-prompts with the valid range |
| Empty input | Re-prompts |
| Pop / peek on empty stack | `STACK UNDERFLOW` message, program continues |
| Dequeue / peek on empty queue | `QUEUE UNDERFLOW` message, program continues |
| Push on full stack / enqueue on full queue | Overflow message, operation rejected |
| Insert into full array | Capacity message, operation rejected |
| Invalid array index or list position | Valid range is shown, operation rejected |
| Delete from an empty structure | Clear message, program continues |
| Traversal from a non-existent vertex | Vertex list is shown, operation rejected |
| Duplicate vertex or duplicate edge | Rejected with a message |
| Self-loop edge | Rejected with a message |

---

## 10. GitHub Collaboration

See `docs/GITHUB_WORKFLOW.md` for the branch, commit and pull request steps.
Each member works on their own branch from their own GitHub account, so the
repository history shows genuine individual development as required by
Section 9 of the assignment brief.

---

## 11. Submission Checklist

- [ ] All team member names filled in above
- [ ] All student IDs filled in above
- [ ] Responsibilities documented
- [ ] Individual contributions documented
- [ ] Code compiles with no errors
- [ ] Every component demonstrated and tested
- [ ] GitHub repository complete with branches, commits and pull requests
- [ ] One merged demonstration video, under 15 minutes
- [ ] Every member's face clearly visible during their own section
- [ ] Google Drive uploaded (if the LMS upload is too large)
- [ ] Editor access granted to `asanka.r@sltc.ac.lk`
- [ ] Editor access granted to `kaushika.w@sltc.ac.lk`
- [ ] Submitted through the LMS link before the deadline
