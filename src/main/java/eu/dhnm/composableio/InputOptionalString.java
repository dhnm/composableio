package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

/**
 * Extension of {@link Input} for {@link String} inputs that can be empty.
 * To make sure that the input is not empty, use {@link InputString}
 * @author Dinh Huy Nhat Minh
 */
public class InputOptionalString extends Input<String> {
    /**
     * No validation and default error message
     * @param message instruction for user to input a value
     */
    public InputOptionalString(String message) {
        super(message);
    }

    /**
     * Custom validator and default error message
     * @param message instruction for user to input a value
     * @param isValid custom validator, return true if validation passed, or false to ask user again
     */
    public InputOptionalString(String message, Function<String, Boolean> isValid) {
        super(message, isValid);
    }

    /**
     * No validation and custom error message
     * @param message instruction for user to input a value
     * @param tryAgainPrompt custom prompt for user to try again upon encountering error
     */
    public InputOptionalString(String message, String tryAgainPrompt) {
        super(message, tryAgainPrompt);
    }

    /**
     * @param message instruction for user to input a value
     * @param isValid validator, return true if validation passed, or false to ask user again
     * @param tryAgainPrompt prompt for user to try again upon encountering error
     */
    public InputOptionalString(String message, Function<String, Boolean> isValid, String tryAgainPrompt) {
        super(message, isValid, tryAgainPrompt);
    }

    @Override
    protected void validationHook() throws InputMismatchException {
        setValue(getScanner().nextLine().trim());
        super.validationHook();
    }
}
