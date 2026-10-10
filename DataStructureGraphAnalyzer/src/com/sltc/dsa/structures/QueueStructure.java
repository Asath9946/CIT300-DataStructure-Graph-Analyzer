package com.sltc.dsa.structures;

import com.sltc.dsa.util.OperationStats;

/**
 * COMPONENT C - QUEUE (FIFO : First In, First Out)
 *
 * Implemented as a CIRCULAR array queue. A circular queue is used instead of
 * a simple linear queue because a linear queue wastes space once the rear
 * pointer reaches the end of the array, even though the front may be empty.
 * The circular design keeps enqueue and dequeue at O(1) with no shifting.
 *
 * Complexity summary
 * ------------------------------------------------------------------
 *  enqueue  O(1)
 *  dequeue  O(1)
 *  peek     O(1)
 *  display  O(n)
 * ------------------------------------------------------------------
 */
public class QueueStructure {

    private final int[] elements;
    private int front;
    private int rear;
    private int count;

    public QueueStructure(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.elements = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == elements.length;
    }

    public int getSize() {
        return count;
    }

    public int getCapacity() {
        return elements.length;
    }

    /** Adds an element at the rear of the queue. O(1). */
    public OperationStats enqueue(int value) {
        long start = System.nanoTime();
        long steps = 1; // full check

        if (isFull()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "QUEUE OVERFLOW - the queue is full (capacity " + elements.length + ").");
        }
        rear = (rear + 1) % elements.length;   // circular wrap-around
        elements[rear] = value;
        count++;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Enqueued " + value + ". Queue size is now " + count + ".");
    }

    /** Removes and returns the element at the front. O(1). */
    public OperationStats dequeue() {
        long start = System.nanoTime();
        long steps = 1; // empty check

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "QUEUE UNDERFLOW - cannot dequeue from an empty queue.");
        }
        int value = elements[front];
        front = (front + 1) % elements.length;
        count--;
        steps++;

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Dequeued " + value + ". Queue size is now " + count + ".");
    }

    /** Returns the front element without removing it. O(1). */
    public OperationStats peek() {
        long start = System.nanoTime();
        long steps = 1;

        if (isEmpty()) {
            return new OperationStats(steps, System.nanoTime() - start, false,
                    "Queue is EMPTY - there is no front element.");
        }
        return new OperationStats(steps + 1, System.nanoTime() - start, true,
                "Front of queue = " + elements[front] + ".");
    }

    /** Prints the queue from front to rear. O(n). */
    public OperationStats display() {
        long start = System.nanoTime();
        long steps = 0;

        if (isEmpty()) {
            System.out.println("        The queue is currently empty.");
            return new OperationStats(0, System.nanoTime() - start, true, "Queue is empty.");
        }

        StringBuilder sb = new StringBuilder("        FRONT -> ");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % elements.length;
            sb.append("[").append(elements[index]).append("] ");
            steps++;
        }
        sb.append("<- REAR");
        System.out.println(sb);
        System.out.println("        size = " + count + " / " + elements.length
                + "   (front index = " + front + ", rear index = " + rear + ")");

        return new OperationStats(steps, System.nanoTime() - start, true,
                "Displayed " + count + " element(s), front first.");
    }

    public void loadSampleData() {
        front = 0;
        rear = -1;
        count = 0;
        int[] sample = {101, 102, 103, 104, 105};
        for (int value : sample) {
            if (!isFull()) {
                rear = (rear + 1) % elements.length;
                elements[rear] = value;
                count++;
            }
        }
    }

    public void clear() {
        front = 0;
        rear = -1;
        count = 0;
    }
}
