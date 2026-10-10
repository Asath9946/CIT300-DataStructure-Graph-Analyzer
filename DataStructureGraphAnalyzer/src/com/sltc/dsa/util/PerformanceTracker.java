package com.sltc.dsa.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects the result of every measured operation performed during the
 * session and prints the Performance Comparison table required by
 * Section 4 of the assignment specification.
 */
public class PerformanceTracker {

    /** A single recorded row of the performance table. */
    public static class Record {
        private final String operation;
        private final String algorithm;
        private final long steps;
        private final long timeNanos;
        private final String result;

        public Record(String operation, String algorithm, long steps, long timeNanos, String result) {
            this.operation = operation;
            this.algorithm = algorithm;
            this.steps = steps;
            this.timeNanos = timeNanos;
            this.result = result;
        }

        public String getOperation() { return operation; }
        public String getAlgorithm() { return algorithm; }
        public long getSteps()       { return steps; }
        public long getTimeNanos()   { return timeNanos; }
        public String getResult()    { return result; }
    }

    private final List<Record> records = new ArrayList<>();

    /** Stores one measurement so it can be displayed later. */
    public void record(String operation, String algorithm, OperationStats stats, String result) {
        records.add(new Record(operation, algorithm, stats.getSteps(), stats.getTimeNanos(), result));
    }

    public void record(String operation, String algorithm, long steps, long timeNanos, String result) {
        records.add(new Record(operation, algorithm, steps, timeNanos, result));
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public int size() {
        return records.size();
    }

    public void clear() {
        records.clear();
    }

    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    /**
     * Prints every recorded measurement in the tabular layout shown in
     * Section 4 of the assignment brief.
     */
    public void displayAll() {
        ConsoleUI.printHeading("PERFORMANCE COMPARISON");
        if (records.isEmpty()) {
            System.out.println("  No operations have been recorded yet.");
            System.out.println("  Run some operations from the menus first, then return here.");
            ConsoleUI.printLine();
            return;
        }

        System.out.printf("%-22s %-22s %10s %14s   %s%n",
                "Operation", "Algorithm / Structure", "Steps", "Time (ms)", "Result");
        System.out.println("-".repeat(115));

        for (Record r : records) {
            System.out.printf("%-22s %-22s %10d %14.6f   %s%n",
                    truncate(r.getOperation(), 22),
                    truncate(r.getAlgorithm(), 22),
                    r.getSteps(),
                    r.getTimeNanos() / 1_000_000.0,
                    truncate(r.getResult(), 40));
        }
        System.out.println("=".repeat(115));
        System.out.println("Total recorded operations : " + records.size());
        System.out.println();
        System.out.println("NOTE: 'Steps' counts elementary operations (comparisons / visits) and is");
        System.out.println("      hardware independent, so it is the reliable column for comparing");
        System.out.println("      algorithmic complexity. 'Time' varies with CPU load and JIT warm-up.");
        ConsoleUI.printLine();
    }

    /**
     * Prints a focused comparison of only the searching rows, which is the
     * clearest demonstration of O(n) versus O(log n).
     */
    public void displaySearchComparison() {
        ConsoleUI.printHeading("SEARCH ALGORITHM COMPARISON");
        boolean found = false;
        System.out.printf("%-22s %10s %14s   %s%n", "Algorithm", "Steps", "Time (ms)", "Result");
        ConsoleUI.printThinLine();
        for (Record r : records) {
            if (r.getOperation().toLowerCase().contains("search")) {
                found = true;
                System.out.printf("%-22s %10d %14.6f   %s%n",
                        truncate(r.getAlgorithm(), 22),
                        r.getSteps(),
                        r.getTimeNanos() / 1_000_000.0,
                        truncate(r.getResult(), 40));
            }
        }
        if (!found) {
            System.out.println("  No search operations recorded yet.");
        }
        ConsoleUI.printLine();
    }

    private String truncate(String text, int max) {
        if (text == null) {
            return "";
        }
        return text.length() <= max ? text : text.substring(0, max - 3) + "...";
    }
}
