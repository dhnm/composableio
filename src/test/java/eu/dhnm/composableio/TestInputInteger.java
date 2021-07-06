package eu.dhnm.composableio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestInputInteger {
    // attributes/variables
    private InputInteger inputInteger;

    @BeforeEach
    void setUp() {
        // initialise attributes
        inputInteger = new InputInteger("Test Input Integer", (input) -> input <= 12, "Validator is set for a number <= 12");
    }

    @Test
    void validationHook() {
        ComposableIO.setScanner(new Scanner("1\n"));
        assertDoesNotThrow(inputInteger::validationHook);
    }

    @Test
    void validationHookThrows() {
        ComposableIO.setScanner(new Scanner("100\n"));
        assertThrows(InputMismatchException.class, inputInteger::validationHook);

        ComposableIO.setScanner(new Scanner("\n"));
        assertThrows(InputMismatchException.class, inputInteger::validationHook);
    }

    @Test
    void init() {
        ComposableIO.setScanner(new Scanner("5\n"));
        assertEquals(5, inputInteger.init().getValue());
    }

    @Test
    void nextLineAfterNextIntScanner() {
        System.setIn(new ByteArrayInputStream("1\none\n".getBytes()));
        ComposableIO.setScanner(new Scanner(System.in));

        inputInteger.init();
        assertEquals("one", new InputString("One Prompt").init().getValue());
    }
}
