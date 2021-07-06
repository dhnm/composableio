package eu.dhnm.composableio;

import java.util.Scanner;

public class ComposableIO {
    private final String message;
    private static Scanner scanner = new Scanner(System.in);

    public ComposableIO(String message) {
        this.message = message;
    }

    public ComposableIO init() {
        System.out.println(message);
        return this;
    }

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

    public static void setScanner(Scanner scanner) {
        ComposableIO.scanner = scanner;
    }
}
