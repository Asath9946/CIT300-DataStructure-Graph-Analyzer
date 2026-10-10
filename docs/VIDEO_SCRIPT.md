# Demonstration Video Script

**Target length: under 15 minutes total.** With five members that is roughly
**2 minutes 30 seconds each**, plus a 30-second intro and a 30-second close.
With four members, about 3 minutes each.

## Rules from the brief you must not break

1. **Every member's face must be clearly visible throughout their own section.**
   Screen-only or voice-only recording will not be accepted as evidence.
   Record with the webcam overlay on (OBS Studio, free: *Display Capture* +
   *Video Capture Device* placed in a corner).
2. Each member records their own section, then the sections are merged into
   **one** video.
3. Every member must introduce themselves with **name + student ID +
   responsibility**.
4. Every member must show their own code, run it, and explain the complexity.
5. **Speak about your own work in your own words.** The lecturer can ask you to
   explain any line you showed. Read `COMPLEXITY_NOTES.md` until you can do this
   without the script in front of you.

## Recording settings

- Resolution 1080p, screen capture of the terminal/IDE + webcam in a corner
- Increase the console font size (IntelliJ: Ctrl+Shift+Plus; Windows Terminal:
  Ctrl+Scroll) so the code is readable on a phone screen
- Quiet room, headset microphone if possible
- Run the program **before** recording so the demo flows without errors

---

## SECTION 0 — Intro (30 seconds, any one member)

> "Assalamu alaikum / Good morning sir. We are Group ____ of CIT300 Data
> Structures and Algorithms. This is our Graded Practical Assignment 2, the
> Data Structure and Graph Performance Analyzer — a Java console application
> that implements Array, Stack, Queue, Linked List, Searching and Graph
> components in one integrated system, with a performance comparison module.
> Each member will now present their own component."

Show on screen: the project folder structure and the main menu running.

---

## SECTION 1 — Array and Searching (2 min 30 s)

**Face on camera. Introduce yourself.**

> "My name is ____, student ID ____. My responsibility was the Array component
> and the Searching algorithms."

**Show the code** — open `ArrayStructure.java`:

> "The array is a fixed-capacity integer array with a `size` counter. Insert at
> the end is O(1) because I write directly into the next free slot. Insert at an
> index is O(n), because this loop here shifts every element after the index one
> place to the right. Delete works the same way in reverse. Every method returns
> an `OperationStats` object carrying the step count and the execution time, and
> that is what feeds the performance table."

Open `SearchAlgorithms.java`:

> "Linear search walks the array from left to right and counts one comparison per
> element, so it is O(n). Binary search needs sorted data: it compares the middle
> element and discards half the range each time, which gives O(log n). I also
> wrote a recursive version and an insertion sort to prepare the data."

**Demonstrate** — run the program:
1. Main menu → `1` Array Operations → `8` Load sample data → `7` Display
2. `1` Insert at end → value `55` → show the step count
3. `2` Insert at index → index `0`, value `99` → **point out the higher step
   count because of the shifting**
4. `5` Search → a value → show the steps
5. `10` back → `5` Searching Operations → `3` Compare Linear vs Binary
6. `4` Compare on a large dataset → enter `10000`

**Explain the result on screen:**

> "Here with 10,000 elements, linear search needed 10,000 comparisons and binary
> search needed 14. That is because log base 2 of 10,000 is about 13.3. But
> binary search only works on sorted data — if I had to sort first that costs
> O(n log n), so for a single search on unsorted data linear search is actually
> the better choice. Binary search pays off when you search the same sorted data
> many times."

**Integration:** "My array is the same object the Searching menu reads from, so
anything I insert here is immediately searchable in the search module."

---

## SECTION 2 — Stack and Queue (2 min 30 s)

**Face on camera. Introduce yourself.**

> "My name is ____, student ID ____. I implemented the Stack and the Queue."

**Show the code** — `StackStructure.java`:

> "The stack is LIFO — last in, first out. It uses an array and a `top` pointer
> starting at minus one. Push increments top and writes; pop reads and
> decrements. Both are O(1) because nothing shifts. The important part is this
> check here: if the stack is empty, pop returns a failed result with a STACK
> UNDERFLOW message instead of crashing, which is what the specification asks
> for."

`QueueStructure.java`:

> "The queue is FIFO. I implemented it as a **circular** queue. In a plain linear
> queue, once rear reaches the end of the array the queue reports full even if
> the front is empty. Using modulo here — rear plus one mod capacity — the index
> wraps around, so enqueue and dequeue both stay O(1) and no space is wasted."

**Demonstrate:**
1. `2` Stack → `6` Load sample → `4` Display (show top-to-bottom)
2. `1` Push a value → `2` Pop → `3` Peek
3. `7` Clear stack → `2` Pop again → **show the STACK UNDERFLOW message**
4. `5` LIFO demonstration → enter 5 numbers → show the reversed output
5. `8` back → `3` Queue → `5` Load sample → `4` Display
6. `2` Dequeue twice → `4` Display → point out that front moved, rear did not
7. `6` Clear → `2` Dequeue → **show QUEUE UNDERFLOW**

**Explain:**

> "Both structures are O(1) for all their main operations. The difference is the
> order: the stack returns the newest item, the queue returns the oldest. This
> matters in the graph component — BFS uses a queue so it explores level by
> level, and DFS uses a stack so it goes deep first."

---

## SECTION 3 — Linked List (2 min 30 s)

**Face on camera. Introduce yourself.**

> "My name is ____, student ID ____. I implemented the Linked List."

**Show the code** — `LinkedListStructure.java`:

> "This is a singly linked list. Each element is a `Node` object holding the data
> and a reference to the next node. The list only keeps a `head` reference.
> Insert at the beginning is O(1) — I point the new node at the old head and move
> head. Insert at the end is O(n) because I have to walk the whole chain to find
> the tail. Delete by value is O(n) because I have to find the node first, and
> then I just bypass it by changing the previous node's `next` pointer — no
> shifting like the array."

Show `reverse()`:

> "Reverse uses three pointers — previous, current and next — and flips every
> link in a single O(n) pass."

**Demonstrate:**
1. `4` Linked List → `9` Load sample → `8` Display (show HEAD -> ... -> NULL)
2. `1` Insert at beginning → **note the low step count**
3. `2` Insert at end → **note the higher step count, explain it is the traversal**
4. `6` Search → show steps
5. `4` Delete by value → `8` Display
6. `7` Reverse → show the reversed chain

**Explain the array vs linked list comparison:**

> "Compared with the array: the array gives O(1) access by index but O(n)
> insertion in the middle. The linked list is the opposite — O(1) insertion at
> the head but O(n) to reach element number k. That is also why we cannot use
> binary search on a linked list: getting to the middle node already costs O(n),
> so the halving gains nothing."

---

## SECTION 4 — Graph, BFS and DFS (3 minutes — this is the mandatory component)

**Face on camera. Introduce yourself.**

> "My name is ____, student ID ____. I implemented the Graph component and the
> traversal algorithms."

**Show the code** — `Graph.java`:

> "The graph is stored as an **adjacency list** — a LinkedHashMap where each
> vertex maps to a list of its neighbours. I chose the adjacency list over an
> adjacency matrix because real networks are sparse: our sample graph has 9
> vertices and 9 edges, so a matrix would need 81 cells, mostly zeros, while the
> list needs only 27 references. The space complexity is O(V + E) instead of
> O(V²)."

Show `bfs()`:

> "BFS uses a queue. I enqueue the start vertex, then repeatedly dequeue a vertex,
> visit it, and enqueue all its unvisited neighbours. Because the queue is FIFO,
> everything one hop away is processed before anything two hops away — that is
> why BFS finds the path with the fewest edges."

Show `dfs()`:

> "DFS uses a stack. I push the start vertex, then pop, visit, and push the
> neighbours. Because the stack is LIFO, the most recently discovered vertex is
> explored next, so the traversal dives down one branch before backtracking.
> I also wrote a recursive version where the JVM call stack replaces my explicit
> stack."

**Demonstrate:**
1. `6` Graph → `13` Load sample graph (Sri Lankan cities)
2. `5` Display adjacency list
3. `6` Display adjacency matrix → **compare the two representations on screen**
4. `1` Add vertex → e.g. `Jaffna` ; `2` Add edge → `Jaffna` and `Colombo`
5. `7` BFS from `Colombo` → read out the visit order
6. `8` DFS from `Colombo` → read out the visit order
7. `11` Compare BFS vs DFS → show the step table
8. `10` Path search → `Colombo` to `ArugamBay` → show the shortest path
9. `12` Check connectivity

**Explain:**

> "Both traversals are O(V + E), because each vertex is visited once and each edge
> is examined once. That is why the step counts here are almost identical — 28
> versus 29. What differs is the order and the memory. BFS can hold a whole level
> in its queue, so worst case O(V) space; DFS only holds the current path, so
> O(depth). And only BFS gives the shortest path in an unweighted graph, which is
> what this path search uses — Colombo to Arugam Bay in 3 hops."

---

## SECTION 5 — Performance, Integration and Testing (2 min 30 s)

**Face on camera. Introduce yourself.**

> "My name is ____, student ID ____. I was responsible for the main menu, the
> integration of all components, the performance comparison module and testing."

**Show the code** — `Main.java`:

> "Main owns one instance of every structure and one shared PerformanceTracker,
> and passes them to every menu. That is what makes this one integrated
> application rather than six separate programs — anything inserted in the Array
> menu is visible in the Searching menu and in Display All Results."

`OperationStats.java` and `PerformanceTracker.java`:

> "Every operation in the system returns an OperationStats object with a step
> count and a nanosecond timing. The tracker collects them and prints the
> comparison table required by the specification."

`InputValidator.java`:

> "All input goes through this class. If the user types text where a number is
> expected, or a menu number out of range, it re-prompts instead of throwing an
> exception. The program cannot be crashed by bad input."

**Demonstrate:**
1. `7` Performance Comparison → `3` Run the automatic benchmark → show the
   complete filled table
2. `4` Complexity reference table
3. `6` back → `8` Display All Results → show every structure plus the table
4. **Error handling demo:** at the main menu type `abc`, then `99`, then a
   negative index in the array menu → show that each one is rejected cleanly

**Explain:**

> "The steps column is the reliable one for comparing algorithms because it is
> hardware independent and maps directly to Big-O. The time column varies with
> CPU load and JVM warm-up — you can see the first measured operation often looks
> slower purely because the JVM has not optimised that code path yet."

---

## SECTION 6 — Close (30 seconds)

> "That completes our demonstration. All required components are implemented —
> Array, Stack, Queue, Linked List, Searching, Graph with BFS and DFS traversal,
> the performance and complexity demonstration, and the integrated console
> application. The full source code, README and commit history are in our GitHub
> repository. Thank you, sir."

Show on screen: the GitHub repository page with the branches and commits visible.

---

## Pre-recording checklist

- [ ] Program compiles and runs with zero errors
- [ ] Sample data loads correctly
- [ ] Console font is large enough to read on a phone
- [ ] Webcam is on and the face is visible for the whole section
- [ ] Name and student ID stated out loud
- [ ] Own code shown on screen
- [ ] Functionality actually run, not just described
- [ ] Complexity explained in own words
- [ ] Integration with the whole system mentioned
- [ ] Total merged video under 15 minutes
