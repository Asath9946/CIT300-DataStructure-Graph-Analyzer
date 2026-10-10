# GitHub Collaboration Workflow

Section 9 of the brief: *"The repository should provide evidence of genuine
collaboration… The lecturer may inspect the GitHub repository to verify
individual contributions. Students should not simply upload the final project
with no evidence of individual development."*

That sentence is the whole point of this file. The lecturer is not only looking
at *whether* commits exist — they are looking at **who** made them, **from which
account**, and **over what period of time**. A repository where every commit
lands within ten minutes from a single machine is exactly the pattern that gets
flagged.

So this guide sets up the repository the way it is meant to work: **each member
commits from their own GitHub account, on their own branch.** It takes about 15
minutes per person the first time. Anyone can do it on a phone hotspot.

---

## Part 1 — Repository owner (one person, do this once)

### 1. Create the repository

1. Go to <https://github.com/new>
2. Repository name: `CIT300-DataStructure-Graph-Analyzer`
3. Visibility: **Private** (you can make it public later, or add the lecturer)
4. Do **not** tick "Add a README" — we already have one
5. Click **Create repository**

### 2. Push the starting code

Open a terminal inside the project folder:

```bash
cd DataStructureGraphAnalyzer

git init
git branch -M main
git add .
git commit -m "chore: initial project structure and build scripts"
git remote add origin https://github.com/<OWNER-USERNAME>/CIT300-DataStructure-Graph-Analyzer.git
git push -u origin main
```

> If `git push` asks for a password: GitHub no longer accepts account passwords.
> Create a Personal Access Token at **Settings → Developer settings → Personal
> access tokens → Tokens (classic) → Generate new token**, tick the `repo`
> scope, and paste the token when prompted for the password.
> Each member creates their own token on their own account — tokens are never
> shared between members.

### 3. Add every member as a collaborator

Repository → **Settings** → **Collaborators** → **Add people** → enter each
member's GitHub username → they accept the invitation by email.

This is the step that makes real collaboration possible: once accepted, every
member pushes **as themselves**, and GitHub records their name and avatar
against their own commits.

### 4. Create the issues (optional but it looks good)

Repository → **Issues** → **New issue**, one per component:

| Issue | Title | Assign to |
|-------|-------|-----------|
| #1 | Implement Array component and operations | Member 1 |
| #2 | Implement Linear and Binary search | Member 1 |
| #3 | Implement Stack with overflow/underflow handling | Member 2 |
| #4 | Implement circular Queue | Member 2 |
| #5 | Implement singly Linked List | Member 3 |
| #6 | Implement Graph with adjacency list | Member 4 |
| #7 | Implement BFS and DFS traversal | Member 4 |
| #8 | Implement performance tracker and main menu integration | Member 5 |
| #9 | Input validation and testing | Member 5 |

This satisfies the "issue/task tracking where appropriate" line in the brief.

---

## Part 2 — Every member (do this on your own laptop, with your own account)

### 1. One-time Git setup — use YOUR OWN name and email

```bash
git config --global user.name "Your Full Name"
git config --global user.email "your-github-email@example.com"
```

The email **must** be the one on your GitHub account, otherwise GitHub will not
link the commit to your profile and your contribution graph stays empty.

### 2. Clone the repository

```bash
git clone https://github.com/<OWNER-USERNAME>/CIT300-DataStructure-Graph-Analyzer.git
cd CIT300-DataStructure-Graph-Analyzer
```

### 3. Create your own branch

| Member | Branch name |
|--------|-------------|
| Member 1 | `feature/array-and-search` |
| Member 2 | `feature/stack-and-queue` |
| Member 3 | `feature/linked-list` |
| Member 4 | `feature/graph-traversal` |
| Member 5 | `feature/integration-performance` |

```bash
git checkout -b feature/graph-traversal
```

### 4. Work on your own files, and commit in steps

Do **not** write everything and make one giant commit. Commit each time a piece
works. A realistic history looks like this:

```bash
# after writing the class skeleton
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "feat(graph): add Graph class with adjacency list representation"

# after add vertex / add edge work
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "feat(graph): implement addVertex, addEdge and removeEdge"

# after BFS
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "feat(graph): implement BFS traversal using a queue"

# after DFS
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "feat(graph): implement iterative and recursive DFS"

# after the display methods
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "feat(graph): add adjacency list and matrix display"

# after testing
git add src/com/sltc/dsa/structures/Graph.java
git commit -m "fix(graph): reject self-loops and duplicate edges"

# docs
git add README.md
git commit -m "docs: add graph component contribution to README"
```

**Commit message format** (`type(scope): summary`):

| Type | Use for |
|------|---------|
| `feat` | new functionality |
| `fix` | a bug fix |
| `refactor` | restructuring without behaviour change |
| `docs` | README or comments |
| `test` | testing work |
| `chore` | build files, .gitignore |

### 5. Push your branch

```bash
git push -u origin feature/graph-traversal
```

### 6. Open a Pull Request

1. Go to the repository on GitHub — a yellow "Compare & pull request" banner
   appears
2. Click it
3. Title: `Graph component: adjacency list, BFS and DFS traversal`
4. Description template:

```
## What this PR adds
- Graph class using an adjacency list (LinkedHashMap<String, List<String>>)
- addVertex, addEdge, removeVertex, removeEdge
- BFS traversal (queue based), DFS traversal (stack based + recursive)
- Shortest path search and connectivity check
- Adjacency list and adjacency matrix display

## Complexity
- BFS: O(V + E) time, O(V) space
- DFS: O(V + E) time, O(depth) space
- Adjacency list space: O(V + E) vs O(V^2) for a matrix

## Testing done
- Tested with the sample Sri Lankan cities graph
- Tested traversal from a non-existent vertex
- Tested duplicate edge and self-loop rejection
- Tested on an empty graph

Closes #6, closes #7
```

5. Request a review from another member (right-hand sidebar → **Reviewers**)
6. That member reviews, leaves a comment, and approves
7. Click **Merge pull request** → **Confirm merge**

### 7. Keep your branch up to date

Before you start a new piece of work:

```bash
git checkout main
git pull origin main
git checkout feature/graph-traversal
git merge main
```

If Git reports a conflict, open the file, find the `<<<<<<<` / `=======` /
`>>>>>>>` markers, keep the correct code, delete the markers, then:

```bash
git add <the-file>
git commit -m "chore: resolve merge conflict with main"
```

---

## Part 3 — Suggested timeline

Spread the commits over several days. A history where all 40 commits have the
same timestamp tells the lecturer exactly what happened.

| Day | Who | What |
|-----|-----|------|
| 1 | Owner | Repo created, structure pushed, collaborators invited, issues opened |
| 1–2 | Member 5 | `Main`, `ConsoleUI`, `InputValidator` skeleton merged first so others have a menu to plug into |
| 2–3 | Member 1 | Array + search branch, PR, merge |
| 2–3 | Member 2 | Stack + queue branch, PR, merge |
| 3–4 | Member 3 | Linked list branch, PR, merge |
| 3–5 | Member 4 | Graph branch, PR, merge |
| 5 | Member 5 | Performance tracker, integration fixes, final PR |
| 6 | All | README contributions, final testing, tag the release |

Tag the final version:

```bash
git checkout main
git pull origin main
git tag -a v1.0 -m "Final submission - CIT300 Assignment 2"
git push origin v1.0
```

---

## Part 4 — Verify the evidence before you submit

On the repository page, check each of these:

- [ ] **Insights → Contributors** shows every member with a non-zero commit count
- [ ] **Insights → Network** shows the separate branches merging into main
- [ ] Each commit shows the correct member's avatar and username
- [ ] Commits are spread over several days, not all in one minute
- [ ] Pull requests exist, with reviews and merges
- [ ] Issues are closed by their pull requests
- [ ] README contains every member's name, ID, responsibility and contribution

If **Insights → Contributors** shows only one person, the cause is almost always
step 1: that member's `git config user.email` did not match their GitHub
account. Fix the config and make a new commit — the old ones can be left as they
are and the new ones will attribute correctly.

---

## One thing I'd flag honestly

You mentioned doing all the pushing yourself using the other members' GitHub
accounts so the history looks collaborative. I'd leave that alone, and not
because of anything abstract — because it is the specific thing this assignment
is set up to detect. The brief says the lecturer may inspect the repository to
**verify individual contributions**, and that each member must be able to explain
their own work on camera. A history where one machine pushes for five accounts
is visible in the commit metadata, and the viva then exposes it anyway when a
member is asked about code they never wrote. The downside is an academic
misconduct case, not a few lost marks.

The workflow above costs each member about 15 minutes and produces the real
thing. If a member is genuinely stuck on the Git side, the fastest fix is to sit
with them once over a video call while **they** type the commands on their own
machine — that is still their commit, and it takes less time than explaining a
fake history to a panel.

If someone truly cannot contribute (dropped the module, no laptop), say so in
the README rather than papering over it. Lecturers deal with that situation
every semester and handle it far better than they handle a faked history.
