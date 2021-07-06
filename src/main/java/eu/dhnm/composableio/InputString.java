package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

public class InputString extends InputOptionalString {


    public InputString(String message) {
        super(message);
    }

    public InputString(String message, Function<String, Boolean> isValid) {
        super(message, isValid);
    }

    public InputString(String message, String errorMessage) {
        super(message, errorMessage);
    }

    public InputString(String message, Function<String, Boolean> isValid, String errorMessage) {
        super(message, isValid, errorMessage);
    }

    @Override
    protected void validationHook() throws InputMismatchException {
        Function<String, Boolean> originalValidator = getIsValid();
        setIsValid((input) -> input.length() > 0 && originalValidator.apply(input));
        super.validationHook();
    }
}
