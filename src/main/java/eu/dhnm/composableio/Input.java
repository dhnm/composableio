package eu.dhnm.composableio;

import java.util.InputMismatchException;
import java.util.function.Function;

/**
 * The base class for Inputs. Input receives a value, which can be validated with the provided validation function.
 * If it does NOT pass the validation, user gets asked repeatedly until it does.
 *
 * This class is only meant to be extended, all its constructors are protected.
 *
 * @author Dinh Huy Nhat Minh
 */
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

    /**
     * Gets the input value and calls the validator if provided.
     * Calls itself again if an InputMismatchException is thrown from the validation process or elsewhere.
     */
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

    /**
     * @return the input value
     */
    public T getValue() {
        return value;
    }

    /**
     * set the input value
     * @param value the input value
     */
    public void setValue(T value) { this.value = value; }

    /**
     * @return the validator function
     */
    public Function<T, Boolean> getIsValid() {
        return isValid;
    }

    /**
     * set the validator function
     * @param isValid the validator function
     */
    public void setIsValid(Function<T, Boolean> isValid) { this.isValid = isValid; }

    /**
     * @return printed message when an error occurs prompting user to try again
     */
    public String getTryAgainPrompt() {
        return tryAgainPrompt;
    }

    /**
     * @param tryAgainPrompt printed message when an error occurs prompting user to try again
     */
    public void setTryAgainPrompt(String tryAgainPrompt) { this.tryAgainPrompt = tryAgainPrompt; }
}
