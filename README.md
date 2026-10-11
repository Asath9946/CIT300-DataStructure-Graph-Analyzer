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

| # | Full Name | Student ID | Assigned Responsibility |
|---|-----------|------------|--------------------------|
| 1 | M. I. Thanish | 23DA2-0897 | Array component + Searching algorithms |
| 2 | M. I. M. Naizar | 23DA2-1052 | Stack component + Queue component |
| 3 | J. F. Asma | 23DA2-0645 | Singly Linked List component |
| 4 | A. H. Arsah Ahmed | 23DA2-0746 | Graph component + BFS/DFS traversal |
| 5 | F. M. Asath | 23DA2-0743 | Integration, Main Menu, Performance Comparison, Testing |

---

## 3. Individual Contributions

**Student Name:** M. I. Thanish
**Student ID:** 23DA2-0897
**Assigned Responsibility:** Array component and Searching algorithms
**Individual Contribution:**
- Implemented the `ArrayStructure` class with insert at end, insert at index,
  delete by index, delete by value, update and display
- Implemented linear search on the array with elementary step counting
- Implemented iterative and recursive binary search in `SearchAlgorithms`
- Implemented the insertion sort helper used to prepare data for binary search
- Built the Linear vs Binary comparison that demonstrates O(n) against O(log n)
  on datasets of up to 100,000 elements
- Tested array boundary conditions: full array, empty array and invalid index

---

**Student Name:** M. I. M. Naizar
**Student ID:** 23DA2-1052
**Assigned Responsibility:** Stack and Queue components
**Individual Contribution:**
- Implemented the `StackStructure` class with push, pop, peek and display
- Added stack overflow and stack underflow handling so the program never crashes
- Implemented the `QueueStructure` class as a circular array queue, keeping
  enqueue and dequeue at O(1) with no element shifting
- Added queue overflow and queue underflow handling
- Implemented the LIFO reversal demonstration
- Tested both structures under empty and full conditions

---

**Student Name:** J. F. Asma
**Student ID:** 23DA2-0645
**Assigned Responsibility:** Singly Linked List component
**Individual Contribution:**
- Implemented the `LinkedListStructure` class with an inner `Node` class
- Implemented insert at beginning, insert at end and insert at position
- Implemented delete by value and delete by position
- Implemented search and in-place reversal using the three-pointer technique
- Documented the comparison between linked list insertion cost and array
  insertion cost
- Tested insertion and deletion on an empty list and at invalid positions

---

**Student Name:** A. H. Arsah Ahmed
**Student ID:** 23DA2-0746
**Assigned Responsibility:** Graph component and traversal algorithms
**Individual Contribution:**
- Implemented the `Graph` class using an adjacency list representation
- Implemented add vertex, add edge, remove vertex and remove edge
- Implemented adjacency list and adjacency matrix display for comparison
- Implemented BFS traversal using a queue
- Implemented DFS traversal using a stack, plus a recursive variant
- Implemented shortest path search between two vertices and the connectivity check
- Tested traversal from a non-existent vertex, duplicate edges and self-loops

---

**Student Name:** F. M. Asath
**Student ID:** 23DA2-0743
**Assigned Responsibility:** Integration, Main Menu, Performance Comparison and Testing
**Individual Contribution:**
- Implemented `Main` as the integration layer that owns one shared instance of
  every data structure, making the system one application rather than six
- Implemented `PerformanceTracker` and `OperationStats` for step and time measurement
- Implemented the performance comparison tables and the automatic benchmark
- Implemented `InputValidator` so that invalid input cannot crash the program
- Implemented `ConsoleUI` for consistent formatting across all menus
- Carried out full system testing and set up the GitHub repository

---

## 4. Technologies Used

| Item | Detail |
|------|--------|
| Language | Java (SE 17 or later; tested on OpenJDK 21) |
| Application type | Console-based system |
| Paradigm | Object-oriented – encapsulation, separation of concerns, one class per data structure |
| External libraries | None. All data structures are implemented from first principles. |
| Version control | Git + GitHub (branches, commits, issues, pull requests) |
| IDE | IntelliJ IDEA / Eclipse / VS Code |

---

## 5. Main System Features

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
- Dedicated Linear vs Binary and BFS vs DFS comparisons with written
  explanations of why the results differ
- One-click automatic benchmark that fills the table across all components
- Complexity reference table for every structure and algorithm

### H. Integration
All six components share one `Main` class and one `PerformanceTracker`
instance, so results recorded in one module are visible in every other module.
Option 8, *Display All Results*, prints the live state of every structure plus
the complete performance table.

---

## 6. Project Structure

```
CIT300-DataStructure-Graph-Analyzer/
├── README.md
├── .gitignore
├── run.sh                       # compile + run on Linux / macOS
├── run.bat                      # compile + run on Windows
├── docs/
│   ├── COMPLEXITY_NOTES.md      # complexity explanations for the viva
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

## 7. Instructions for Running the Program

### Requirement
Java JDK 17 or later. Check with:

```bash
java -version
javac -version
```

### Option 1 – one command (recommended)

**Windows (Command Prompt or PowerShell):**
```
.\run.bat
```

**Linux / macOS:**
```bash
chmod +x run.sh
./run.sh
```

### Option 2 – manual compile and run

**Windows (Command Prompt):**
```cmd
mkdir build
dir /s /B src\*.java > sources.txt
javac -d build @sources.txt
java -cp build com.sltc.dsa.Main
```

**Windows (PowerShell):**
```powershell
javac -d build (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp build com.sltc.dsa.Main
```

**Linux / macOS / Git Bash:**
```bash
mkdir -p build
javac -d build $(find src -name "*.java")
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

## 8. Main Menu

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

## 9. Sample Performance Output

```
Operation              Algorithm / Structure       Steps      Time (ms)
-------------------------------------------------------------------------
Search (n=10000)       Linear Search               10000       0.325239
Search (n=10000)       Binary Search                  14       0.001735
Graph Traversal        BFS                            28       1.731812
Graph Traversal        DFS                            29       0.643665
Insert                 Array (at end)                  2       0.001213
Insert                 Linked List (at end)            7       0.002470
Insert                 Stack (push)                    2       0.002625
Insert                 Queue (enqueue)                 2       0.002727
```

**Why linear search took 10000 steps and binary search took 14:** linear search
examines every element until it finds the target, so the worst case is n
comparisons. Binary search discards half the remaining range on every
comparison, so it needs at most log2(n) comparisons — log2(10000) is about 13.3.

**Why BFS and DFS have almost the same step count:** both are O(V + E) because
each vertex is visited once and each edge is examined once. What differs is the
visit order and the memory profile, not the complexity.

Full explanations are in `docs/COMPLEXITY_NOTES.md`.

---

## 10. Complexity Reference

| Structure / Algorithm | Access | Search | Insert / Delete | Space |
|---|---|---|---|---|
| Array | O(1) | O(n) | O(n) | O(n) |
| Stack (array) | O(1) top | O(n) | O(1) | O(n) |
| Queue (circular) | O(1) front | O(n) | O(1) | O(n) |
| Singly Linked List | O(n) | O(n) | O(1) at head | O(n) + pointers |
| Linear Search | – | O(n) | – | O(1) |
| Binary Search | – | O(log n) | – | O(1) iterative |
| Insertion Sort | – | – | O(n²) | O(1) |
| Graph BFS | – | O(V+E) | – | O(V) |
| Graph DFS | – | O(V+E) | – | O(depth) |
| Adjacency List | O(degree) | O(V+E) | O(1) add edge | O(V+E) |
| Adjacency Matrix | O(1) | O(V²) | O(1) add edge | O(V²) |

---

## 11. Input Validation and Error Handling

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

## 12. Version Control

This project was developed using Git and GitHub. The repository contains:

- Feature branches for each component
- Descriptive commit messages following the `type(scope): summary` convention
- Issues created for each component and assigned to the responsible member
- Pull requests used to merge completed work into `main`

Repository: https://github.com/Asath9946/CIT300-DataStructure-Graph-Analyzer

---

## 13. Submission Checklist

- [x] All team member names and student IDs recorded
- [x] Responsibilities documented
- [x] Individual contributions documented
- [x] Code compiles with no errors
- [x] Every component implemented and tested
- [x] GitHub repository with commits, issues and pull requests
- [ ] One merged demonstration video, under 15 minutes
- [ ] Every member's face clearly visible during their own section
- [ ] Google Drive uploaded (if the LMS upload is too large)
- [ ] Editor access granted to `asanka.r@sltc.ac.lk`
- [ ] Editor access granted to `kaushika.w@sltc.ac.lk`
- [ ] Submitted through the LMS link
