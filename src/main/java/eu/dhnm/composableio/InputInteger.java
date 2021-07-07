package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

public class InputInteger extends Input<Integer> {

    public InputInteger(String message) {
        super(message);
    }

    public InputInteger(String message, Function<Integer, Boolean> isValid) {
        super(message, isValid);
    }

    public InputInteger(String message, String errorMessage) {
        super(message, errorMessage);
    }

    public InputInteger(String message, Function<Integer, Boolean> isValid, String errorMessage) {
        super(message, isValid, errorMessage);
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
