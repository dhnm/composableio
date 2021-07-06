package eu.dhnm.composableio;

public class Action extends ComposableIO {
    private final Runnable callback;

    public Action(String message, Runnable callback) {
        super(message);
        this.callback = callback;
    }

    @Override
    public Action init() {
        super.init();
        this.callback.run();
        return this;
    }
}
