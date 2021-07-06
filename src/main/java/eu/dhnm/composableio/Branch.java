package eu.dhnm.composableio;

public class Branch extends Action {
    public Branch(String message, Menu menu) {
        super(message, menu::init);
    }
}
