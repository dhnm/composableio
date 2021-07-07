package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

/**
 * Extension of {@link Input} for {@link String} inputs with a default validator refusing empty inputs.
 * To accept empty input as a valid answer, use {@link InputOptionalString}
 * @author Dinh Huy Nhat Minh
 */
public class InputString extends InputOptionalString {
    /**
     * No validation and default error message
     * @param message instruction for user to input a value
     */
    public InputString(String message) {
        super(message);
    }

    /**
     * Custom validator and default error message
     * @param message instruction for user to input a value
     * @param isValid custom validator, return true if validation passed, or false to ask user again
     */
    public InputString(String message, Function<String, Boolean> isValid) {
        super(message, isValid);
    }

    /**
     * No validation and custom error message
     * @param message instruction for user to input a value
     * @param tryAgainPrompt custom prompt for user to try again upon encountering error
     */
    public InputString(String message, String tryAgainPrompt) {
        super(message, tryAgainPrompt);
    }

    /**
     * @param message instruction for user to input a value
     * @param isValid validator, return true if validation passed, or false to ask user again
     * @param tryAgainPrompt prompt for user to try again upon encountering error
     */
    public InputString(String message, Function<String, Boolean> isValid, String tryAgainPrompt) {
        super(message, isValid, tryAgainPrompt);
    }

    @Override
    protected void validationHook() throws InputMismatchException {
        Function<String, Boolean> originalValidator = getIsValid();
        setIsValid((input) -> input.length() > 0 && originalValidator.apply(input));
        super.validationHook();
    }
}
