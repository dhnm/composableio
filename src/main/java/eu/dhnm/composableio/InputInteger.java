package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Extension of {@link Input} for {@link Integer} inputs.
 * @author Dinh Huy Nhat Minh
 */
public class InputInteger extends Input<Integer> {
    /**
     * No validation and default error message
     * @param message instruction for user to input a value
     */
    public InputInteger(String message) {
        super(message);
    }

    /**
     * Custom validator and default error message
     * @param message instruction for user to input a value
     * @param isValid custom validator
     */
    public InputInteger(String message, Function<Integer, Boolean> isValid) {
        super(message, isValid);
    }

    /**
     * No validation and custom error message
     * @param message instruction for user to input a value
     * @param tryAgainPrompt custom prompt for user to try again upon encountering error
     */
    public InputInteger(String message, String tryAgainPrompt) {
        super(message, tryAgainPrompt);
    }

    /**
     * @param message instruction for user to input a value
     * @param isValid validator
     * @param tryAgainPrompt prompt for user to try again upon encountering error
     */
    public InputInteger(String message, Function<Integer, Boolean> isValid, String tryAgainPrompt) {
        super(message, isValid, tryAgainPrompt);
    }

    @Override
    protected void validationHook() throws InputMismatchException {
        Scanner scanner = getScanner();

        try {
            setValue(scanner.nextInt());
            scanner.nextLine();
        } catch(InputMismatchException e) {
            scanner.nextLine();
            throw new InputMismatchException("Wrong Input");
        } catch (NoSuchElementException e) {
            throw new InputMismatchException("Empty Input");
        }

        super.validationHook();
    }
}
