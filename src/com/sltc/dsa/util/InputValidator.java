package com.sltc.dsa.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Centralised input validation for the whole application.
 *
 * Section 8 of the specification requires that user input is validated and
 * that invalid operations are handled appropriately. Every menu in this
 * project reads user input through this class, so no menu can crash with an
 * InputMismatchException.
 */
public class InputValidator {

    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads an integer, re-prompting until the user types a valid whole number.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLineSafely();
            if (line == null) {
                // Input stream closed (e.g. piped input finished) - exit cleanly.
                System.out.println();
                ConsoleUI.printInfo("Input stream ended. Exiting application.");
                System.exit(0);
            }
            line = line.trim();
            if (line.isEmpty()) {
                ConsoleUI.printError("Input cannot be empty. Please enter a whole number.");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                ConsoleUI.printError("'" + line + "' is not a valid integer. Please try again.");
            }
        }
    }

    /**
     * Reads an integer that must fall inside an inclusive range.
     * Used for all menu choices.
     */
    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value < min || value > max) {
                ConsoleUI.printError("Please enter a number between " + min + " and " + max + ".");
                continue;
            }
            return value;
        }
    }

    /**
     * Reads a non-empty single-word label, used for graph vertex names.
     */
    public String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLineSafely();
            if (line == null) {
                System.out.println();
                ConsoleUI.printInfo("Input stream ended. Exiting application.");
                System.exit(0);
            }
            line = line.trim();
            if (line.isEmpty()) {
                ConsoleUI.printError("Input cannot be empty. Please try again.");
                continue;
            }
            if (line.contains(" ")) {
                ConsoleUI.printError("Please enter a single word with no spaces (e.g. Colombo).");
                continue;
            }
            return line;
        }
    }

    /** Reads a yes/no confirmation. */
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String line = readLineSafely();
            if (line == null) {
                return false;
            }
            line = line.trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            ConsoleUI.printError("Please answer with y or n.");
        }
    }

    /** Pauses so the user can read the output before the menu reprints. */
    public void pause() {
        System.out.print("\nPress ENTER to continue...");
        readLineSafely();
    }

    private String readLineSafely() {
        try {
            if (!scanner.hasNextLine()) {
                return null;
            }
            return scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return null;
        }
    }
}
