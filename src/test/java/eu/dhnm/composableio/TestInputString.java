package eu.dhnm.composableio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestInputString {
    // attributes/variables
    private InputString inputString;

    @BeforeEach
    void setUp() {
        // initialise attributes
        inputString = new InputString("Test Input String", (input) -> input.length() <= 12, "Input is limited to length 12");
    }

    @Test
    void validationHook() {
        ComposableIO.setScanner(new Scanner("I am valid\n"));
        assertDoesNotThrow(inputString::validationHook);
    }

    @Test
    void validationHookThrows() {
        ComposableIO.setScanner(new Scanner("I am more than twelve characters long\n"));
        assertThrows(InputMismatchException.class, inputString::validationHook);

        ComposableIO.setScanner(new Scanner("\n"));
        assertThrows(InputMismatchException.class, inputString::validationHook);
    }

    @Test
    void init() {
        ComposableIO.setScanner(new Scanner("Hello World\n"));
        assertEquals("Hello World", inputString.init().getValue());
    }
}