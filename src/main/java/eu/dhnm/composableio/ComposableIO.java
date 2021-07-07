package eu.dhnm.composableio;

import java.util.Scanner;

/**
 * The base class that all other classes extend from. Provides common fields and methods.
 * Stores a static Scanner for terminal input for reuse.
 * @author Dinh Huy Nhat Minh
 */
public class ComposableIO {
    private final String message;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param message a title for the item; shown when Menu is displayed and printed on init()
     */
    protected ComposableIO(String message) {
        this.message = message;
    }

    /**
     * Print the message
     */
    public ComposableIO init() {
        System.out.println(message);
        return this;
    }

    /**
     * Utility method for getting an array of ComposableIO objects from an arbitrary array.
     * This is useful when putting inside Menu for presenting a selection.
     * The {@link Menu#getChoiceIdx()} method can then return the index of the selected item.
     * @param array an array to translate to ComposableIO, results of toString() will be used for the message field
     * @param <E> type of the elements in the array
     * @return array of ComposableIO objects
     */
    public static <E> ComposableIO[] toItems(E[] array) {
        ComposableIO[] items = new ComposableIO[array.length];
        for (int i = 0; i < array.length; i++) {
            items[i] = new ComposableIO(array[i].toString());
        }
        return items;
    }

    public String getMessage() {
        return message;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Replace the default Scanner with a custom one. It is stored in a static field to be reused.
     * @param scanner the custom Scanner
     */
    public static void setScanner(Scanner scanner) {
        ComposableIO.scanner = scanner;
    }
}
