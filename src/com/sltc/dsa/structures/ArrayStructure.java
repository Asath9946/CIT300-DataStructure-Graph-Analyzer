package com.sltc.dsa.structures;

import com.sltc.dsa.util.OperationStats;

/**
 * COMPONENT A - ARRAY
 *
 * A fixed-capacity integer array implemented from first principles (no
 * java.util.ArrayList) so that the cost of each operation is visible.
 *
 * Complexity summary
 * ------------------------------------------------------------------
 *  insertAtEnd      O(1)   - write directly into the next free slot
 *  insertAtIndex    O(n)   - elements after the index must shift right
 *  deleteAtIndex    O(n)   - elements after the index must shift left
 *  deleteByValue    O(n)   - linear scan, then a shift
 *  search           O(n)   - linear scan
 *  display          O(n)   - every element is printed
 * ------------------------------------------------------------------
 */
public class ArrayStructure {

    private final int[] elements;
    private int size;

    public ArrayStructure(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.elements = new int[capacity];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    /** Returns a defensive copy of the live data, used by the search module. */
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = elements[i];
        }
        return copy;
    }

    /**
     * Inserts a value at the end of the array. O(1).
     */
    public OperationStats insertAtEnd(int value) {
        long start = System.nanoTime();
        long steps = 0;

        steps++; // capacity check
        if (isFull()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Array is FULL (capacity " + elements.length + "). Insert rejected.");
        }
        elements[size] = value;
        size++;
        steps++; // the single write

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Inserted " + value + " at index " + (size - 1) + ".");
    }

    /**
     * Inserts a value at a specific index, shifting the tail to the right. O(n).
     */
    public OperationStats insertAtIndex(int index, int value) {
        long start = System.nanoTime();
        long steps = 0;

        steps++;
        if (isFull()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Array is FULL. Insert rejected.");
        }
        steps++;
        if (index < 0 || index > size) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Invalid index " + index + ". Valid range is 0 to " + size + ".");
        }

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
            steps++; // one shift
        }
        elements[index] = value;
        size++;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Inserted " + value + " at index " + index + ".");
    }

    /**
     * Deletes the element at a given index, shifting the tail left. O(n).
     */
    public OperationStats deleteAtIndex(int index) {
        long start = System.nanoTime();
        long steps = 0;

        steps++;
        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Array is EMPTY. Nothing to delete.");
        }
        steps++;
        if (index < 0 || index >= size) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Invalid index " + index + ". Valid range is 0 to " + (size - 1) + ".");
        }

        int removed = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            steps++;
        }
        size--;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Deleted value " + removed + " from index " + index + ".");
    }

    /**
     * Deletes the first occurrence of a value. O(n) search plus O(n) shift.
     */
    public OperationStats deleteByValue(int value) {
        long start = System.nanoTime();
        long steps = 0;

        steps++;
        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Array is EMPTY. Nothing to delete.");
        }

        int foundIndex = -1;
        for (int i = 0; i < size; i++) {
            steps++; // one comparison
            if (elements[i] == value) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Value " + value + " was not found in the array.");
        }

        for (int i = foundIndex; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            steps++;
        }
        size--;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Deleted value " + value + " that was at index " + foundIndex + ".");
    }

    /**
     * Sequentially searches for a value. O(n).
     */
    public OperationStats search(int value) {
        long start = System.nanoTime();
        long steps = 0;

        for (int i = 0; i < size; i++) {
            steps++;
            if (elements[i] == value) {
                return new OperationStats(steps, System.nanoTime() - start, true,
                        "Value " + value + " FOUND at index " + i + ".");
            }
        }
        return new OperationStats(steps, System.nanoTime() - start, false,
                "Value " + value + " NOT FOUND in the array.");
    }

    /**
     * Updates the element stored at an index. O(1).
     */
    public OperationStats update(int index, int value) {
        long start = System.nanoTime();
        long steps = 1;

        if (index < 0 || index >= size) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Invalid index " + index + ".");
        }
        int old = elements[index];
        elements[index] = value;
        steps++;
        return new OperationStats(steps, System.nanoTime() - start, true,
                "Index " + index + " updated from " + old + " to " + value + ".");
    }

    /**
     * Prints the array contents with index labels. O(n).
     */
    public OperationStats display() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The array is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Array is empty.");
        }

        StringBuilder indexRow = new StringBuilder("        Index : ");
        StringBuilder valueRow = new StringBuilder("        Value : ");
        for (int i = 0; i < size; i++) {
            indexRow.append(String.format("%6d", i));
            valueRow.append(String.format("%6d", elements[i]));
            steps++;
        }
        System.out.println(indexRow);
        System.out.println(valueRow);
        System.out.println("        Size = " + size + " / Capacity = " + elements.length);

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed " + size + " element(s).");
    }

    /** Loads demonstration data so the video demo can start quickly. */
    public void loadSampleData() {
        size = 0;
        int[] sample = {45, 12, 78, 33, 91, 5, 60, 27, 84, 19};
        for (int value : sample) {
            if (!isFull()) {
                elements[size++] = value;
            }
        }
    }

    public void clear() {
        size = 0;
    }
}
