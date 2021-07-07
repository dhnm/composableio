package eu.dhnm.composableio;

/**
 * A Menu item that immediately performs an action when selected (calls the provided callback function)
 * @author Dinh Huy Nhat Minh
 */
public class Action extends ComposableIO {
    private final Runnable callback;

    /**
     * @param callback function for the action to call on init()
     */
    public Action(String message, Runnable callback) {
        super(message);
        this.callback = callback;
    }

    /**
     * Calls the custom function
     */
    @Override
    public Action init() {
        super.init();
        this.callback.run();
        return this;
    }
}
