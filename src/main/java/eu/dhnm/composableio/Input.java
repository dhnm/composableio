package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

public class Input<T> extends ComposableIO {
    private T value = null;

    private Function<T, Boolean> isValid;
    private String tryAgainPrompt = "Wrong input. Please try again.";

    protected Input(String message) {
        this(message, (T t) -> true, null);
    }

    protected Input(String message, Function<T, Boolean> isValid) {
        this(message, isValid, null);
    }

    protected Input(String message, String tryAgainPrompt) {
        this(message, (T t) -> true, tryAgainPrompt);
    }

    protected Input(String message, Function<T, Boolean> isValid, String tryAgainPrompt) {
        super(message);
        this.isValid = isValid;
        if (tryAgainPrompt != null) this.tryAgainPrompt = tryAgainPrompt;
    }

    @Override
    public Input<T> init() {
        super.init();

        try {
            validationHook();
            return this;
        } catch (InputMismatchException e) {
            System.out.println(getTryAgainPrompt());
        }

        return this.init();
    }

    protected void validationHook() throws InputMismatchException {
        if (!getIsValid().apply(getValue())) throw new InputMismatchException();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) { this.value = value; }

    public Function<T, Boolean> getIsValid() {
        return isValid;
    }

    public void setIsValid(Function<T, Boolean> isValid) { this.isValid = isValid; }

    public String getTryAgainPrompt() {
        return tryAgainPrompt;
    }

    public void setTryAgainPrompt(String tryAgainPrompt) { this.tryAgainPrompt = tryAgainPrompt; }
}
