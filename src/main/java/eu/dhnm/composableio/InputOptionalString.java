package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

public class InputOptionalString extends Input<String> {
    public InputOptionalString(String message) {
        super(message);
    }

    public InputOptionalString(String message, Function<String, Boolean> isValid) {
        super(message, isValid);
    }

    public InputOptionalString(String message, String errorMessage) {
        super(message, errorMessage);
    }

    public InputOptionalString(String message, Function<String, Boolean> isValid, String errorMessage) {
        super(message, isValid, errorMessage);
    }

    @Override
    protected void validationHook() throws InputMismatchException {
        setValue(getScanner().nextLine().trim());
        super.validationHook();
    }
}
