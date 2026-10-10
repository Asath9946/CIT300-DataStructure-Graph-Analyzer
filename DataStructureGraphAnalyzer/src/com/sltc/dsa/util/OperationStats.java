package com.sltc.dsa.util;

/**
 * Immutable value object that carries the measurement of a single operation.
 *
 * Every data-structure operation in this project returns an OperationStats
 * object so that the Performance Comparison module can compare algorithms
 * using two independent measurements:
 *
 *   steps     -> the number of basic/elementary operations performed.
 *                This is machine independent and maps directly to Big-O.
 *   timeNanos -> wall clock execution time in nanoseconds.
 *                This is machine dependent and only indicative.
 */
public class OperationStats {

    private final long steps;
    private final long timeNanos;
    private final boolean successful;
    private final String message;

    public OperationStats(long steps, long timeNanos, boolean successful, String message) {
        this.steps = steps;
        this.timeNanos = timeNanos;
        this.successful = successful;
        this.message = message;
    }

    public long getSteps() {
        return steps;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    /** Execution time converted to milliseconds for display purposes. */
    public double getTimeMillis() {
        return timeNanos / 1_000_000.0;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message + " | steps = " + steps + " | time = " + timeNanos + " ns";
    }
}
