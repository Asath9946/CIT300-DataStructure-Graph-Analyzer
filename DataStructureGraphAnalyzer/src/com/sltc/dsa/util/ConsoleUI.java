package com.sltc.dsa.util;

/**
 * Small helper class that keeps all console formatting in one place so that
 * every menu in the application looks consistent.
 */
public class ConsoleUI {

    public static final int WIDTH = 60;

    public static void printLine() {
        System.out.println("=".repeat(WIDTH));
    }

    public static void printThinLine() {
        System.out.println("-".repeat(WIDTH));
    }

    /** Prints a centred, boxed heading. */
    public static void printHeading(String title) {
        System.out.println();
        printLine();
        int padding = Math.max(0, (WIDTH - title.length()) / 2);
        System.out.println(" ".repeat(padding) + title);
        printLine();
    }

    public static void printSubHeading(String title) {
        System.out.println();
        System.out.println("--- " + title + " " + "-".repeat(Math.max(0, WIDTH - title.length() - 5)));
    }

    public static void printSuccess(String message) {
        System.out.println("[OK]    " + message);
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printInfo(String message) {
        System.out.println("[INFO]  " + message);
    }

    public static void printStats(OperationStats stats) {
        System.out.printf("        Steps: %d  |  Time: %.6f ms%n",
                stats.getSteps(), stats.getTimeMillis());
    }
}
