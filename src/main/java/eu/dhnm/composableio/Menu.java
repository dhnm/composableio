package eu.dhnm.composableio;

public class Menu extends ComposableIO {
    private final ComposableIO[] items;
    private int choiceIdx;

    public Menu(String message, ComposableIO[] items) {
        super("\n" + message);
        this.items = items;
    }

    @Override
    public Menu init() {
        super.init();

        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + " " + items[i].getMessage());
        }

        this.choiceIdx = new InputInteger(
                getMessage(),
                (Integer c) -> c > 0 && c <= items.length,
                "Invalid choice, please try again:"
        ).init().getValue() - 1;
        items[choiceIdx].init();

        return this;
    }

    public int getChoiceIdx() {
        return choiceIdx;
    }
}
