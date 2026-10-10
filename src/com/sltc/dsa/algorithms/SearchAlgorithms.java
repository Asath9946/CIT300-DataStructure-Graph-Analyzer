package com.sltc.dsa.algorithms;

import com.sltc.dsa.util.OperationStats;

/**
 * COMPONENT E - SEARCHING
 *
 * Contains Linear Search and Binary Search, plus the sorting helper that
 * binary search depends on. Every method counts the number of comparisons
 * it performs so that the Performance Comparison module can show the
 * difference between O(n) and O(log n) with real numbers.
 */
public class SearchAlgorithms {

    /** The index found by the last search, or -1. */
    private int lastFoundIndex = -1;

    public int getLastFoundIndex() {
        return lastFoundIndex;
    }

    /**
     * LINEAR SEARCH - checks each element from left to right.
     *
     *   Best case    O(1)   - the target is the first element
     *   Average case O(n/2) -> O(n)
     *   Worst case   O(n)   - the target is last or absent
     *   Works on unsorted data.
     */
    public OperationStats linearSearch(int[] data, int target) {
        long start = System.nanoTime();
        long steps = 0;
        lastFoundIndex = -1;

        for (int i = 0; i < data.length; i++) {
            steps++;   // one comparison
            if (data[i] == target) {
                lastFoundIndex = i;
                return new OperationStats(steps, System.nanoTime() - start, true,
                        "FOUND at index " + i + " after " + steps + " comparison(s).");
            }
        }
        return new OperationStats(steps, System.nanoTime() - start, false,
                "NOT FOUND after " + steps + " comparison(s).");
    }

    /**
     * BINARY SEARCH - repeatedly halves a SORTED array.
     *
     *   Best case    O(1)      - the target is the middle element
     *   Average/worst O(log n)
     *   PRECONDITION: the array must already be sorted in ascending order.
     */
    public OperationStats binarySearch(int[] sortedData, int target) {
        long start = System.nanoTime();
        long steps = 0;
        lastFoundIndex = -1;

        int low = 0;
        int high = sortedData.length - 1;

        while (low <= high) {
            steps++;   // one comparison per halving
            int mid = low + (high - low) / 2;   // avoids integer overflow

            if (sortedData[mid] == target) {
                lastFoundIndex = mid;
                return new OperationStats(steps, System.nanoTime() - start, true,
                        "FOUND at index " + mid + " after " + steps + " comparison(s).");
            } else if (sortedData[mid] < target) {
                low = mid + 1;        // discard the left half
            } else {
                high = mid - 1;       // discard the right half
            }
        }
        return new OperationStats(steps, System.nanoTime() - start, false,
                "NOT FOUND after " + steps + " comparison(s).");
    }

    /**
     * Recursive binary search, included to show the same O(log n) logic
     * expressed recursively.
     */
    public OperationStats binarySearchRecursive(int[] sortedData, int target) {
        long start = System.nanoTime();
        long[] steps = new long[1];
        lastFoundIndex = recursiveHelper(sortedData, target, 0, sortedData.length - 1, steps);

        boolean found = lastFoundIndex != -1;
        String message = found
                ? "FOUND at index " + lastFoundIndex + " after " + steps[0] + " comparison(s)."
                : "NOT FOUND after " + steps[0] + " comparison(s).";
        return new OperationStats(steps[0], System.nanoTime() - start, found, message);
    }

    private int recursiveHelper(int[] data, int target, int low, int high, long[] steps) {
        if (low > high) {
            return -1;
        }
        steps[0]++;
        int mid = low + (high - low) / 2;
        if (data[mid] == target) {
            return mid;
        }
        if (data[mid] < target) {
            return recursiveHelper(data, target, mid + 1, high, steps);
        }
        return recursiveHelper(data, target, low, mid - 1, steps);
    }

    /**
     * Insertion sort used to prepare data for binary search.
     * O(n^2) worst case. This cost is important: binary search is only
     * worthwhile when the data is already sorted or will be searched
     * many times.
     */
    public OperationStats insertionSort(int[] data) {
        long start = System.nanoTime();
        long steps = 0;

        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
                steps++;
            }
            data[j + 1] = key;
            steps++;
        }
        return new OperationStats(steps, System.nanoTime() - start, true,
                "Array sorted in ascending order using " + steps + " operation(s).");
    }

    /** Returns a sorted copy without modifying the caller's array. */
    public int[] sortedCopy(int[] data) {
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        insertionSort(copy);
        return copy;
    }

    /** Formats an int array for console output. */
    public static String format(int[] data) {
        if (data.length == 0) {
            return "(empty)";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
            if (i < data.length - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    /**
     * Generates ascending test data of a given size, used for the
     * large-dataset comparison that makes O(n) vs O(log n) obvious.
     */
    public static int[] generateSortedDataset(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (i + 1) * 2;   // 2, 4, 6, 8 ...
        }
        return data;
    }
}
