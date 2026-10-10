package com.sltc.dsa.structures;

import com.sltc.dsa.util.OperationStats;

/**
 * COMPONENT B - STACK (LIFO : Last In, First Out)
 *
 * Implemented with a fixed-size backing array and a top pointer.
 * Underflow (pop/peek on an empty stack) and overflow (push on a full stack)
 * are both handled gracefully, as required by the specification.
 *
 * Complexity summary
 * ------------------------------------------------------------------
 *  push    O(1)
 *  pop     O(1)
 *  peek    O(1)
 *  display O(n)
 * ------------------------------------------------------------------
 */
public class StackStructure {

    private final int[] elements;
    private int top;   // index of the topmost element, -1 when empty

    public StackStructure(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.elements = new int[capacity];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elements.length - 1;
    }

    public int getSize() {
        return top + 1;
    }

    public int getCapacity() {
        return elements.length;
    }

    /** Adds an element on top of the stack. O(1). */
    public OperationStats push(int value) {
        long start = System.nanoTime();
        long steps = 1; // overflow check

        if (isFull()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "STACK OVERFLOW - the stack is full (capacity " + elements.length + ").");
        }
        top++;
        elements[top] = value;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Pushed " + value + ". Stack size is now " + getSize() + ".");
    }

    /** Removes and returns the top element. O(1). */
    public OperationStats pop() {
        long start = System.nanoTime();
        long steps = 1; // underflow check

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "STACK UNDERFLOW - cannot pop from an empty stack.");
        }
        int value = elements[top];
        top--;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Popped " + value + ". Stack size is now " + getSize() + ".");
    }

    /** Returns the top element without removing it. O(1). */
    public OperationStats peek() {
        long start = System.nanoTime();
        long steps = 1;

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Stack is EMPTY - there is no top element to peek.");
        }
        return new OperationStats(steps + 1, System.nanoTime() - start, true,
                "Top of stack = " + elements[top] + ".");
    }

    /** Prints the stack from top to bottom. O(n). */
    public OperationStats display() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The stack is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Stack is empty.");
        }

        System.out.println("        (top of stack)");
        for (int i = top; i >= 0; i--) {
            System.out.printf("        | %-8d |%s%n", elements[i], (i == top ? "  <-- TOP" : ""));
            steps++;
        }
        System.out.println("        +----------+");
        System.out.println("        (bottom of stack)   size = " + getSize() + " / " + elements.length);

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed " + getSize() + " element(s), top first.");
    }

    /**
     * Practical demonstration of LIFO: reverses a sequence of numbers by
     * pushing all of them and then popping them back out.
     * This shows WHY a stack is useful, not just HOW it works.
     */
    public OperationStats demonstrateReversal(int[] values) {
        long start = System.nanoTime();
        long steps = 0;

        StackStructure temp = new StackStructure(Math.max(values.length, 1));
        StringBuilder original = new StringBuilder();
        for (int v : values) {
            temp.push(v);
            original.append(v).append(" ");
            steps++;
        }

        StringBuilder reversed = new StringBuilder();
        while (!temp.isEmpty()) {
            reversed.append(temp.elements[temp.top]).append(" ");
            temp.top--;
            steps++;
        }

        System.out.println("        Original order : " + original.toString().trim());
        System.out.println("        LIFO output    : " + reversed.toString().trim());

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Reversed " + values.length + " values using LIFO order.");
    }

    public void loadSampleData() {
        top = -1;
        int[] sample = {10, 20, 30, 40, 50};
        for (int value : sample) {
            if (!isFull()) {
                elements[++top] = value;
            }
        }
    }

    public void clear() {
        top = -1;
    }
}
