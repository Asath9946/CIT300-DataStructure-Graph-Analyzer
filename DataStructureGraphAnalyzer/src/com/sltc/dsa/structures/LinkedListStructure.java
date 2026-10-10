package com.sltc.dsa.structures;

import com.sltc.dsa.util.OperationStats;

/**
 * COMPONENT D - SINGLY LINKED LIST
 *
 * Each element is stored in a Node object that holds the data and a
 * reference to the next node. Unlike the array, the linked list grows
 * dynamically and never needs to shift elements, but it cannot jump
 * directly to an index, so random access costs O(n).
 *
 * Complexity summary
 * ------------------------------------------------------------------
 *  insertAtBeginning  O(1)
 *  insertAtEnd        O(n)   (O(1) if a tail pointer is kept)
 *  insertAtPosition   O(n)
 *  deleteByValue      O(n)
 *  search             O(n)
 *  display            O(n)
 *  reverse            O(n)
 * ------------------------------------------------------------------
 */
public class LinkedListStructure {

    /** Node of the singly linked list. */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedListStructure() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    /** Inserts a new node at the front of the list. O(1). */
    public OperationStats insertAtBeginning(int value) {
        long start = System.nanoTime();
        long steps = 0;

        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
        steps += 2;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Inserted " + value + " at the beginning. Size = " + size + ".");
    }

    /** Inserts a new node at the end of the list. O(n) - must walk to the tail. */
    public OperationStats insertAtEnd(int value) {
        long start = System.nanoTime();
        long steps = 0;

        Node node = new Node(value);
        steps++;

        if (head == null) {
            head = node;
            size++;
            return new OperationStats(steps + 1, System.nanoTime() - start, true,
                    "Inserted " + value + " as the first node. Size = " + size + ".");
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
            steps++;   // one traversal step
        }
        current.next = node;
        size++;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Inserted " + value + " at the end. Size = " + size + ".");
    }

    /** Inserts at a 0-based position. O(n). */
    public OperationStats insertAtPosition(int position, int value) {
        long start = System.nanoTime();
        long steps = 1;

        if (position < 0 || position > size) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Invalid position " + position + ". Valid range is 0 to " + size + ".");
        }
        if (position == 0) {
            OperationStats s = insertAtBeginning(value);
            return new OperationStats(steps + s.getSteps(), System.nanoTime() - start, true, s.getMessage());
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            steps++;
        }
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        size++;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Inserted " + value + " at position " + position + ". Size = " + size + ".");
    }

    /** Deletes the first node holding the given value. O(n). */
    public OperationStats deleteByValue(int value) {
        long start = System.nanoTime();
        long steps = 1;

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Linked list is EMPTY. Nothing to delete.");
        }

        if (head.data == value) {
            head = head.next;
            size--;
            steps++;
            return new OperationStats(steps, System.nanoTime() - start, true,
                    "Deleted head node holding " + value + ". Size = " + size + ".");
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
            steps++;
        }

        if (current.next == null) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Value " + value + " was not found in the linked list.");
        }

        current.next = current.next.next;
        size--;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Deleted node holding " + value + ". Size = " + size + ".");
    }

    /** Deletes the node at a 0-based position. O(n). */
    public OperationStats deleteAtPosition(int position) {
        long start = System.nanoTime();
        long steps = 1;

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Linked list is EMPTY. Nothing to delete.");
        }
        if (position < 0 || position >= size) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Invalid position " + position + ". Valid range is 0 to " + (size - 1) + ".");
        }

        if (position == 0) {
            int removed = head.data;
            head = head.next;
            size--;
            return new OperationStats(steps + 1, System.nanoTime() - start, true,
                    "Deleted " + removed + " from position 0. Size = " + size + ".");
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            steps++;
        }
        int removed = current.next.data;
        current.next = current.next.next;
        size--;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Deleted " + removed + " from position " + position + ". Size = " + size + ".");
    }

    /** Sequentially searches the list. O(n). */
    public OperationStats search(int value) {
        long start = System.nanoTime();
        long steps = 0;

        Node current = head;
        int position = 0;
        while (current != null) {
            steps++;   // one comparison
            if (current.data == value) {
                return new OperationStats(steps, System.nanoTime() - start, true,
                        "Value " + value + " FOUND at position " + position + ".");
            }
            current = current.next;
            position++;
        }
        return new OperationStats(steps, System.nanoTime() - start, false,
                "Value " + value + " NOT FOUND in the linked list.");
    }

    /** Reverses the list in place using three pointers. O(n). */
    public OperationStats reverse() {
        long start = System.nanoTime();
        long steps = 0;

        Node previous = null;
        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
            steps++;
        }
        head = previous;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Linked list reversed in place using " + steps + " pointer moves.");
    }

    /** Prints the chain of nodes. O(n). */
    public OperationStats display() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The linked list is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Linked list is empty.");
        }

        StringBuilder sb = new StringBuilder("        HEAD -> ");
        Node current = head;
        while (current != null) {
            sb.append("[").append(current.data).append("] -> ");
            current = current.next;
            steps++;
        }
        sb.append("NULL");
        System.out.println(sb);
        System.out.println("        Number of nodes = " + size);

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed " + size + " node(s).");
    }

    /** Copies the list into an array, used by the searching module. */
    public int[] toArray() {
        int[] values = new int[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            values[i++] = current.data;
            current = current.next;
        }
        return values;
    }

    public void loadSampleData() {
        head = null;
        size = 0;
        int[] sample = {7, 14, 21, 28, 35, 42};
        for (int value : sample) {
            insertAtEnd(value);
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }
}
